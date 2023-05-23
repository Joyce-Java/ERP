package cn.tedu.codeafterend.service.impl;

import cn.tedu.codeafterend.ex.ServiceException;
import cn.tedu.codeafterend.mapper.*;
import cn.tedu.codeafterend.pojo.entity.Bank;
import cn.tedu.codeafterend.pojo.entity.SalaryManagement;
import cn.tedu.codeafterend.pojo.vo.*;
import cn.tedu.codeafterend.service.ISalaryManagementService;
import cn.tedu.codeafterend.utils.LaborLnsurance;
import cn.tedu.codeafterend.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SalaryManagementServiceImpl implements ISalaryManagementService {

    @Autowired
    EmployeeDataMapper employeeDataMapper;

    @Autowired
    OvertimeMapper overtimeMapper;

    @Autowired
    BankMapper bankMapper;

    @Autowired
    SalaryManagementMapper salaryManagementMapper;

    @Autowired
    AbsenceTrackerMapper absenceTrackerMapper;

    @Override
    public void calculateSalary() {
        // 获取当前时间
        LocalDate currentDate = LocalDate.now();
        // 获取上一个月份
        LocalDate lastMonthDate = currentDate.minusMonths(1);
        // 格式化日期
        String formattedDate = lastMonthDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        // 確認上個月是否已經計算過
        int actualMonth = salaryManagementMapper.selectLastMonthData(formattedDate);
        if (actualMonth > 1) {
            String massage = "上個月的薪資已計算過,請次月再嘗試";
            log.debug(massage);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, massage);
        }

        // 查詢所有員工的ID
        List<EmployeeDataStandardVO> ids =
                employeeDataMapper.selectAllId();

        for (EmployeeDataStandardVO employeeDataStandardVO : ids) {
            // 查詢所有員工的加班時數
            List<OvertimeStandardVO> byOvertimeCalculates = overtimeMapper.getByOvertimeCalculate(employeeDataStandardVO.getId());
            // 查詢所有員工的請假時數
            AbsenceTrackerVO byAbsenceTrackerCalculate = absenceTrackerMapper.getByAbsenceTrackerCalculate(employeeDataStandardVO.getId());
            // 查詢所有員工的工資
            BankStandardVO standardById = bankMapper.getStandardById(employeeDataStandardVO.getId());
            // 員工薪資
            Integer salary = standardById.getSalary();
            int weekdayOvertimePay = 0;
            int holidayOvertimePay = 0;
            int legalHolidayOvertimePay = 0;
            int statutoryOvertimePay = 0;

            /**
             * 如果加班時數不等於0則調用對應的加班時數的方法,計算後返回加班費
             */
            if (!byOvertimeCalculates.isEmpty()) {
                for (OvertimeStandardVO overtimeStandardVO : byOvertimeCalculates) {
                    if (overtimeStandardVO.getWeekdayOvertime() != null) {
                        if (overtimeStandardVO.getCompTime()!=null){
                           overtimeStandardVO.setWeekdayOvertime(overtimeStandardVO.getWeekdayOvertime() - overtimeStandardVO.getCompTime());
                        }
                        int weekdayOvertimePayTemporary = weekdayOvertime(overtimeStandardVO.getWeekdayOvertime(), salary);
                        weekdayOvertimePay += weekdayOvertimePayTemporary;
                    }

                    if (overtimeStandardVO.getHolidayOvertime() != null) {
                        if (overtimeStandardVO.getCompTime()!=null){
                            overtimeStandardVO.setHolidayOvertime(overtimeStandardVO.getHolidayOvertime() - overtimeStandardVO.getCompTime());
                        }
                        int holidayOvertimePayPayTemporary = holidayOvertime(overtimeStandardVO.getHolidayOvertime(), salary);
                        holidayOvertimePay += holidayOvertimePayPayTemporary;
                    }

                    if (overtimeStandardVO.getLegalHolidayOvertime() != null) {
                        if (overtimeStandardVO.getCompTime()!=null){
                            overtimeStandardVO.setLegalHolidayOvertime(overtimeStandardVO.getLegalHolidayOvertime() - overtimeStandardVO.getCompTime());
                        }
                        int legalHolidayOvertimePayPayTemporary = legalHolidayOvertime(overtimeStandardVO.getLegalHolidayOvertime(), salary);
                        legalHolidayOvertimePay += legalHolidayOvertimePayPayTemporary;
                    }

                    if (overtimeStandardVO.getStatutoryOvertime() != null) {
                        if (overtimeStandardVO.getCompTime()!=null){
                            overtimeStandardVO.setStatutoryOvertime(overtimeStandardVO.getStatutoryOvertime() - overtimeStandardVO.getCompTime());
                        }
                        int statutoryOvertimePayPayTemporary = statutoryOvertime(overtimeStandardVO.getStatutoryOvertime(), salary);
                        statutoryOvertimePay += statutoryOvertimePayPayTemporary;
                    }

                }
            }
            int personalLeave = 0;
            int sickLeave = 0;
            int menstrualLeave = 0;
            int paternityLeave = 0;

            /**
             * 如果請假時數不等於0則調用對應的加班時數的方法,計算後返回加班費
             */
            if (byAbsenceTrackerCalculate != null) {

                if (byAbsenceTrackerCalculate.getPersonalLeave() != null) {
                    personalLeave = calculatePersonalLeave(byAbsenceTrackerCalculate.getPersonalLeave(), salary);
                }
                if (byAbsenceTrackerCalculate.getSickLeave() != null) {
                    sickLeave = calculateSickLeaveAndMenstrualLeave(byAbsenceTrackerCalculate.getSickLeave(), salary);
                }
                if (byAbsenceTrackerCalculate.getMenstrualLeave() != null) {
                    menstrualLeave = calculateSickLeaveAndMenstrualLeave(byAbsenceTrackerCalculate.getMenstrualLeave(), salary);
                }
                if (byAbsenceTrackerCalculate.getPaternityLeave() != null) {
                    paternityLeave = calculatePaternityLeave(byAbsenceTrackerCalculate.getPaternityLeave(), salary, employeeDataStandardVO.getHiredate());
                }
            }
            int laborInsurance = 0;
            int nationalHealthInsurance = 0;

            //判斷他上個月在職天數
            int daysLastMonth = getDaysLastMonth(employeeDataStandardVO.getHiredate(), employeeDataStandardVO.getQuitdate());

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1); // 上個月
            int previousMonth = calendar.get(Calendar.MONTH); // 取得上個月的月份，0 表示 1 月，1 表示 2 月，以此類推

            //確認上個月是否為二月,因為二月天數問題需要另外處理
            if (previousMonth == Calendar.FEBRUARY) {
                //如果上個月是二月份要另外處理
                //TODO
                if (daysLastMonth >= 28 ) {
                    daysLastMonth = 30;
                    laborInsurance = LaborLnsurance.laborInsuranceCalculation(standardById.getSalary(), daysLastMonth);
                    nationalHealthInsurance = LaborLnsurance.nationalHealthInsurance(standardById.getSalary());
                }else if (daysLastMonth < 28 && daysLastMonth > 0){
                    daysLastMonth += 1;
                    laborInsurance = LaborLnsurance.laborInsuranceCalculation(standardById.getSalary(), daysLastMonth);
                    nationalHealthInsurance = LaborLnsurance.nationalHealthInsurance(standardById.getSalary());
                }

            } else {

                if (daysLastMonth < 30 && daysLastMonth > 0) {
                    daysLastMonth += 1;
                    laborInsurance = LaborLnsurance.laborInsuranceCalculation(standardById.getSalary(), daysLastMonth);
                    nationalHealthInsurance = LaborLnsurance.nationalHealthInsurance(standardById.getSalary());
                } else if (daysLastMonth >= 30) {
                    laborInsurance = standardById.getLaborInsurance();
                    nationalHealthInsurance = standardById.getNationalHealthInsurance();
                } else if (daysLastMonth <= 0) {
                    continue;
                }
            }


            salary = salary - laborInsurance - nationalHealthInsurance;

            int overtimePay = weekdayOvertimePay + holidayOvertimePay + legalHolidayOvertimePay + statutoryOvertimePay;
            int deductedPayForLeave = personalLeave + sickLeave + menstrualLeave + paternityLeave;
            salary += overtimePay;
            salary -= deductedPayForLeave;


            SalaryManagement salaryManagement = new SalaryManagement();
            salaryManagement.setOvertimePay(overtimePay);
            salaryManagement.setActualMonth(formattedDate);
            salaryManagement.setEmployeeId(employeeDataStandardVO.getId());
            salaryManagement.setPayroll(salary);
            salaryManagement.setLeaveDeductionAmount(deductedPayForLeave);
            salaryManagement.setLaborInsurance(laborInsurance);
            salaryManagement.setNationalHealthInsurance(nationalHealthInsurance);
            int rows = salaryManagementMapper.insert(salaryManagement);
            if(rows < 0){
                String message = "計算失敗，伺服器繁忙，請稍後再嘗試。";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERR_INSERT,message);
            }


            System.out.println(employeeDataStandardVO.getEmployeeName() + "薪資為-----" + salary);

        }

    }

    @Override
    public List<SalaryManagementStandardVO> list() {
        // 获取当前时间
        LocalDate currentDate = LocalDate.now();
        // 获取上一个月份
        LocalDate lastMonthDate = currentDate.minusMonths(1);
        // 格式化日期
        String formattedDate = lastMonthDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        return salaryManagementMapper.list(formattedDate);
    }

    @Override
    public List<SalaryManagementStandardVO> getByMonthSelectList(String month) {
        return salaryManagementMapper.getByMonthSelectList(month);
    }


    /**
     * 計算事假扣款金額
     *
     * @param PersonalLeaveHours 事假時數
     * @param salary             薪資
     * @return 計算完扣款金額
     */
    public int calculatePersonalLeave(Double PersonalLeaveHours, Integer salary) {
        BigDecimal bdSalary = new BigDecimal(salary);

        BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.FLOOR)
                .divide(new BigDecimal("8"), 10, RoundingMode.FLOOR)
                .multiply(new BigDecimal(PersonalLeaveHours))
                .setScale(0, RoundingMode.FLOOR);
        int intValue = result.intValue();
        System.out.println(intValue);
        return intValue;
    }

    /**
     * 計算病假/生理假扣款金額
     *
     * @param SickLeave 病假時數
     * @param salary    薪資
     * @return 計算完扣款金額
     */
    public int calculateSickLeaveAndMenstrualLeave(Double SickLeave, Integer salary) {
        BigDecimal bdSalary = new BigDecimal(salary);

        BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.FLOOR)
                .divide(new BigDecimal("8"), 10, RoundingMode.FLOOR)
                .divide(new BigDecimal("2"), 10, RoundingMode.FLOOR)
                .multiply(new BigDecimal(SickLeave))
                .setScale(0, RoundingMode.FLOOR);
        int intValue = result.intValue();
        System.out.println(intValue);
        return intValue;
    }

    /**
     * 計算產假扣款金額
     *
     * @param SickLeave 產假時數
     * @param salary    薪資
     * @return 計算完扣款金額
     */
    public int calculatePaternityLeave(Double SickLeave, Integer salary, String hiredate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate hireDate = LocalDate.parse(hiredate, formatter);
        LocalDate now = LocalDate.now();
        long monthDiff = ChronoUnit.MONTHS.between(hireDate, now);
        if (hireDate.plusMonths(6).isBefore(now)) {
            return 0;
        } else {
            BigDecimal bdSalary = new BigDecimal(salary);

            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.FLOOR)
                    .divide(new BigDecimal("8"), 10, RoundingMode.FLOOR)
                    .divide(new BigDecimal("2"), 10, RoundingMode.FLOOR)
                    .multiply(new BigDecimal(SickLeave))
                    .setScale(0, RoundingMode.FLOOR);
            int intValue = result.intValue();
            System.out.println(intValue);
            return intValue;
        }


    }


    /**
     * 計算在職天數
     *
     * @param hiredate 入職時間
     * @param quitdate 離職時間
     * @return 在職天數
     */
    public int getDaysLastMonth(String hiredate, String quitdate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date joinDate = null;
        Date leaveDate = null;
        try {
            joinDate = dateFormat.parse(hiredate);
            if (quitdate != null) {
                leaveDate = dateFormat.parse(quitdate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // 上個月
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is 0-based, so add 1 to get actual month
        int firstDayOfMonth = 1;
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Month is 0-based, so subtract 1 to set correctly
        calendar.set(Calendar.DAY_OF_MONTH, firstDayOfMonth);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
        Date endDate = calendar.getTime();

        Date overlapStart;
        if (joinDate.before(startDate)) {
            overlapStart = startDate;
        } else if (joinDate.after(endDate)) {
            overlapStart = endDate;
        } else {
            overlapStart = joinDate;
        }

        Date overlapEnd;
        if (leaveDate == null || leaveDate.after(endDate)) {
            overlapEnd = endDate;
        } else if (leaveDate.before(startDate)) {
            overlapEnd = startDate;
        } else {
            overlapEnd = leaveDate;
        }

        int overlapDays = (int) TimeUnit.DAYS.convert(overlapEnd.getTime() - overlapStart.getTime(), TimeUnit.MILLISECONDS);

        return overlapDays;
    }

    /**
     * 平日加班費用計算
     *
     * @param resultOvertime 加班時數
     * @param salary         薪資
     * @return 計算後的加班費率
     */
    public int weekdayOvertime(Double resultOvertime, Integer salary) {
        if (resultOvertime == 0){
            return 0;
        }
        if (resultOvertime <= 2) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal(resultOvertime))
                    .multiply(new BigDecimal("1.34"))
                    .setScale(0, RoundingMode.UP);

            int intValue = result.intValue();
            System.out.println(intValue);
            return intValue;
        } else if (resultOvertime > 2) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .multiply(new BigDecimal("1.34"))
                    .setScale(0, RoundingMode.UP);
            resultOvertime -= 2;


            BigDecimal result1 = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal(resultOvertime))
                    .multiply(new BigDecimal("1.67"))
                    .setScale(0, RoundingMode.UP);

            //425
            int intValue = result.intValue();
            System.out.println("intValue=" + intValue);
            //529
            int intValue1 = result1.intValue();
            System.out.println("intValue1=" + intValue1);
            intValue += intValue1;

            return intValue;
        } else {
            String massage = "系統出現異常,請聯繫管理員";
            log.debug(massage);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, massage);
        }
    }

    /**
     * 休假加班費用計算
     *
     * @param holidayOvertime 加班時數
     * @param salary          薪資
     * @return 計算後的加班費用
     */
    private int holidayOvertime(Double holidayOvertime, Integer salary) {
        if (holidayOvertime == 0){
            return 0;
        }
        if (holidayOvertime <= 2) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal(holidayOvertime))
                    .multiply(new BigDecimal("1.34"))
                    .setScale(0, RoundingMode.UP);

            int intValue = result.intValue();
            System.out.println(intValue);
            return intValue;
        } else if (holidayOvertime > 2 && holidayOvertime <= 8) {

            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .multiply(new BigDecimal("1.34"))
                    .setScale(0, RoundingMode.UP);
            holidayOvertime -= 2;


            BigDecimal result1 = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal(holidayOvertime))
                    .multiply(new BigDecimal("1.67"))
                    .setScale(0, RoundingMode.UP);

            //425
            int intValue = result.intValue();
            System.out.println("intValue=" + intValue);
            //529
            int intValue1 = result1.intValue();
            System.out.println("intValue1=" + intValue1);
            intValue += intValue1;
            System.out.println(intValue);
            return intValue;
        } else if (holidayOvertime > 8 && holidayOvertime <= 12) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .multiply(new BigDecimal("1.34"))
                    .setScale(0, RoundingMode.UP);


            BigDecimal result1 = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("6"))
                    .multiply(new BigDecimal("1.67"))
                    .setScale(0, RoundingMode.UP);

            holidayOvertime -= 8;
            System.out.println(holidayOvertime);
            BigDecimal result2 = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal(holidayOvertime))
                    .multiply(new BigDecimal("2.67"))
                    .setScale(0, RoundingMode.UP);


            //425
            int intValue = result.intValue();
            System.out.println("intValue=" + intValue);
            //529
            int intValue1 = result1.intValue();
            System.out.println("intValue1=" + intValue1);

            int intValue2 = result2.intValue();
            System.out.println("intValue2=" + intValue2);
            intValue += intValue1 + intValue2;
            System.out.println(intValue);
            return intValue;

        } else {
            String massage = "系統出現異常,請聯繫管理員";
            log.debug(massage);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, massage);
        }

    }

    /**
     * 例假加班費用計算
     *
     * @param legalHolidayOvertime 加班時數
     * @param salary               薪資
     * @return 計算後的加班費用
     */
    private int legalHolidayOvertime(Double legalHolidayOvertime, Integer salary) {
        if (legalHolidayOvertime == 0){
            return 0;
        }
        //TODO 待確認做一給八的細節
        if (legalHolidayOvertime >= 0.5 && legalHolidayOvertime <= 8) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .setScale(0, RoundingMode.UP);
            int intValue = result.intValue();
            System.out.println(intValue);
            return intValue;
        } else if (legalHolidayOvertime > 8 && legalHolidayOvertime <= 12) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .setScale(0, RoundingMode.UP);


            legalHolidayOvertime -= 8;

            BigDecimal result1 = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal(legalHolidayOvertime))
                    .multiply(new BigDecimal("2"))
                    .setScale(0, RoundingMode.UP);


            int intValue = result.intValue();
            int intValue1 = result1.intValue();
            intValue += intValue1;
            System.out.println(intValue);
            return intValue;
        } else {
            String massage = "系統出現異常,請聯繫管理員";
            log.debug(massage);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, massage);
        }

    }

    /**
     * 國定/特休加班費用計算
     *
     * @param statutoryOvertime 加班時數
     * @param salary            薪資
     * @return 計算後的加班費用
     */
    private int statutoryOvertime(Double statutoryOvertime, Integer salary) {
        if (statutoryOvertime == 0){
            return 0;
        }
        if (statutoryOvertime > 0.5 && statutoryOvertime <= 8) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .setScale(0, RoundingMode.UP);
            int intValue = result.intValue();
            System.out.println(intValue);
            return intValue;
        } else if (statutoryOvertime > 8 && statutoryOvertime <= 10) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .setScale(0, RoundingMode.UP);


            statutoryOvertime -= 8;

            BigDecimal result1 = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal(statutoryOvertime))
                    .multiply(new BigDecimal("1.34"))
                    .setScale(0, RoundingMode.UP);


            int intValue = result.intValue();
            int intValue1 = result1.intValue();
            intValue += intValue1;
            System.out.println(intValue);
            return intValue;
        } else if (statutoryOvertime > 8 && statutoryOvertime <= 12) {
            BigDecimal bdSalary = new BigDecimal(salary);
            BigDecimal result = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .setScale(0, RoundingMode.UP);


            BigDecimal result1 = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal("2"))
                    .multiply(new BigDecimal("1.34"))
                    .setScale(0, RoundingMode.UP);

            statutoryOvertime -= 10;

            BigDecimal result2 = bdSalary.divide(new BigDecimal("30"), 10, RoundingMode.DOWN)
                    .divide(new BigDecimal("8"), 10, RoundingMode.DOWN)
                    .multiply(new BigDecimal(statutoryOvertime))
                    .multiply(new BigDecimal("1.67"))
                    .setScale(0, RoundingMode.UP);

            int intValue = result.intValue();
            System.out.println("intValue=" + intValue);
            //529
            int intValue1 = result1.intValue();
            System.out.println("intValue1=" + intValue1);

            int intValue2 = result2.intValue();
            System.out.println("intValue2=" + intValue2);
            intValue += intValue1 + intValue2;
            System.out.println(intValue);
            return intValue;

        } else {
            String massage = "系統出現異常,請聯繫管理員";
            log.debug(massage);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, massage);
        }
    }


}
