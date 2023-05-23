package cn.tedu.codeafterend.utils;

public class LaborLnsurance {


    private static int[] laborInsuranceCalculationArray = {11100, 12540, 13500, 15840, 16500, 17280, 17880, 19047, 20008, 21009, 22000, 23100,
            24000, 25250, 26400, 27600, 28800, 30300, 31800, 33300, 34800, 36300, 38200, 40100, 42000, 43900, 45800};

    private static int[] nationalHealthInsuranceArray = {26400, 27600, 28800, 30300, 31800, 33300, 34800, 36300, 38200, 40100, 42000, 43900,
            45800, 48200, 50600, 53000, 55400, 57800, 60800, 63800, 66800, 69800, 72800, 76500, 80200, 83900, 87600, 92100, 96600, 101100, 105600,
            110100, 115500, 120900, 126300, 131700, 137100, 142500, 147900, 150000, 156400, 162800, 169200, 175600, 182000, 189500, 197000, 204500, 212000, 219500};

    private static int[] nationalHealthInsurance = {409, 428, 447, 470, 493, 516, 540, 563, 592, 622, 651, 681, 710, 748, 785, 822, 859, 896, 943, 990, 1036, 1083, 1129, 1187, 1244, 1301, 1359, 1428, 1498, 1568, 1638, 1708, 1791, 1875, 1959, 2043, 2126, 2210, 2294, 2327, 2426, 2525, 2624, 2724, 2823, 2939, 3055, 3172, 3288, 3404};


    public static int nationalHealthInsurance(int salary) {
        int national;
        if (salary <= nationalHealthInsuranceArray[0]) {
            national = nationalHealthInsurance[0];

        } else if (salary <= nationalHealthInsuranceArray[1]) {
            national = nationalHealthInsurance[1];

        } else if (salary <= nationalHealthInsuranceArray[2]) {
            national = nationalHealthInsurance[2];

        } else if (salary <= nationalHealthInsuranceArray[3]) {
            national = nationalHealthInsurance[3];

        } else if (salary <= nationalHealthInsuranceArray[4]) {
            national = nationalHealthInsurance[4];

        } else if (salary <= nationalHealthInsuranceArray[5]) {
            national = nationalHealthInsurance[5];

        } else if (salary <= nationalHealthInsuranceArray[6]) {
            national = nationalHealthInsurance[6];

        } else if (salary <= nationalHealthInsuranceArray[7]) {
            national = nationalHealthInsurance[7];

        } else if (salary <= nationalHealthInsuranceArray[8]) {
            national = nationalHealthInsurance[8];

        } else if (salary <= nationalHealthInsuranceArray[9]) {
            national = nationalHealthInsurance[9];

        } else if (salary <= nationalHealthInsuranceArray[10]) {
            national = nationalHealthInsurance[10];

        } else if (salary <= nationalHealthInsuranceArray[11]) {
            national = nationalHealthInsurance[11];

        } else if (salary <= nationalHealthInsuranceArray[12]) {
            national = nationalHealthInsurance[12];

        } else if (salary <= nationalHealthInsuranceArray[13]) {
            national = nationalHealthInsurance[13];

        } else if (salary <= nationalHealthInsuranceArray[14]) {
            national = nationalHealthInsurance[14];

        } else if (salary <= nationalHealthInsuranceArray[15]) {
            national = nationalHealthInsurance[15];

        } else if (salary <= nationalHealthInsuranceArray[16]) {
            national = nationalHealthInsurance[16];

        } else if (salary <= nationalHealthInsuranceArray[17]) {
            national = nationalHealthInsurance[17];

        } else if (salary <= nationalHealthInsuranceArray[18]) {
            national = nationalHealthInsurance[18];

        } else if (salary <= nationalHealthInsuranceArray[19]) {
            national = nationalHealthInsurance[19];

        } else if (salary <= nationalHealthInsuranceArray[20]) {
            national = nationalHealthInsurance[20];

        } else if (salary <= nationalHealthInsuranceArray[21]) {
            national = nationalHealthInsurance[21];

        } else if (salary <= nationalHealthInsuranceArray[22]) {
            national = nationalHealthInsurance[22];

        } else if (salary <= nationalHealthInsuranceArray[23]) {
            national = nationalHealthInsurance[23];

        } else if (salary <= nationalHealthInsuranceArray[24]) {
            national = nationalHealthInsurance[24];

        } else if (salary <= nationalHealthInsuranceArray[25]) {
            national = nationalHealthInsurance[25];

        } else  if (salary <= nationalHealthInsuranceArray[26]) {
            national = nationalHealthInsurance[26];

        } else  if (salary <= nationalHealthInsuranceArray[27]) {
            national = nationalHealthInsurance[27];

        } else  if (salary <= nationalHealthInsuranceArray[28]) {
            national = nationalHealthInsurance[28];

        } else  if (salary <= nationalHealthInsuranceArray[29]) {
            national = nationalHealthInsurance[29];

        } else  if (salary <= nationalHealthInsuranceArray[30]) {
            national = nationalHealthInsurance[30];

        } else  if (salary <= nationalHealthInsuranceArray[31]) {
            national = nationalHealthInsurance[31];

        } else  if (salary <= nationalHealthInsuranceArray[32]) {
            national = nationalHealthInsurance[32];

        } else  if (salary <= nationalHealthInsuranceArray[33]) {
            national = nationalHealthInsurance[33];

        } else  if (salary <= nationalHealthInsuranceArray[34]) {
            national = nationalHealthInsurance[34];

        } else  if (salary <= nationalHealthInsuranceArray[35]) {
            national = nationalHealthInsurance[35];

        } else  if (salary <= nationalHealthInsuranceArray[36]) {
            national = nationalHealthInsurance[36];

        } else  if (salary <= nationalHealthInsuranceArray[37]) {
            national = nationalHealthInsurance[37];

        } else  if (salary <= nationalHealthInsuranceArray[38]) {
            national = nationalHealthInsurance[38];

        } else  if (salary <= nationalHealthInsuranceArray[39]) {
            national = nationalHealthInsurance[39];

        } else  if (salary <= nationalHealthInsuranceArray[40]) {
            national = nationalHealthInsurance[40];

        } else  if (salary <= nationalHealthInsuranceArray[41]) {
            national = nationalHealthInsurance[41];

        } else  if (salary <= nationalHealthInsuranceArray[42]) {
            national = nationalHealthInsurance[42];

        } else  if (salary <= nationalHealthInsuranceArray[43]) {
            national = nationalHealthInsurance[43];

        } else  if (salary <= nationalHealthInsuranceArray[44]) {
            national = nationalHealthInsurance[44];

        } else  if (salary <= nationalHealthInsuranceArray[45]) {
            national = nationalHealthInsurance[45];

        }else  if (salary <= nationalHealthInsuranceArray[46]) {
            national = nationalHealthInsurance[46];

        }else  if (salary <= nationalHealthInsuranceArray[47]) {
            national = nationalHealthInsurance[47];

        }else  if (salary <= nationalHealthInsuranceArray[48]) {
            national = nationalHealthInsurance[48];

        }else {
            national = nationalHealthInsurance[49];

        }
        return national;
    }


    public static int assessLaborInsuranceTier(int salary) {
        int insurancePremiumTier;
        if (salary <= laborInsuranceCalculationArray[0]) {
            insurancePremiumTier = laborInsuranceCalculationArray[0];

        } else if (salary <= laborInsuranceCalculationArray[1]) {
            insurancePremiumTier = laborInsuranceCalculationArray[1];

        } else if (salary <= laborInsuranceCalculationArray[2]) {
            insurancePremiumTier = laborInsuranceCalculationArray[2];

        } else if (salary <= laborInsuranceCalculationArray[3]) {
            insurancePremiumTier = laborInsuranceCalculationArray[3];

        } else if (salary <= laborInsuranceCalculationArray[4]) {
            insurancePremiumTier = laborInsuranceCalculationArray[4];

        } else if (salary <= laborInsuranceCalculationArray[5]) {
            insurancePremiumTier = laborInsuranceCalculationArray[5];

        } else if (salary <= laborInsuranceCalculationArray[6]) {
            insurancePremiumTier = laborInsuranceCalculationArray[6];

        } else if (salary <= laborInsuranceCalculationArray[7]) {
            insurancePremiumTier = laborInsuranceCalculationArray[7];

        } else if (salary <= laborInsuranceCalculationArray[8]) {
            insurancePremiumTier = laborInsuranceCalculationArray[8];

        } else if (salary <= laborInsuranceCalculationArray[9]) {
            insurancePremiumTier = laborInsuranceCalculationArray[9];

        } else if (salary <= laborInsuranceCalculationArray[10]) {
            insurancePremiumTier = laborInsuranceCalculationArray[10];

        } else if (salary <= laborInsuranceCalculationArray[11]) {
            insurancePremiumTier = laborInsuranceCalculationArray[11];

        } else if (salary <= laborInsuranceCalculationArray[12]) {
            insurancePremiumTier = laborInsuranceCalculationArray[12];

        } else if (salary <= laborInsuranceCalculationArray[13]) {
            insurancePremiumTier = laborInsuranceCalculationArray[13];

        } else if (salary <= laborInsuranceCalculationArray[14]) {
            insurancePremiumTier = laborInsuranceCalculationArray[14];

        } else if (salary <= laborInsuranceCalculationArray[15]) {
            insurancePremiumTier = laborInsuranceCalculationArray[15];

        } else if (salary <= laborInsuranceCalculationArray[16]) {
            insurancePremiumTier = laborInsuranceCalculationArray[16];

        } else if (salary <= laborInsuranceCalculationArray[17]) {
            insurancePremiumTier = laborInsuranceCalculationArray[17];

        } else if (salary <= laborInsuranceCalculationArray[18]) {
            insurancePremiumTier = laborInsuranceCalculationArray[18];

        } else if (salary <= laborInsuranceCalculationArray[19]) {
            insurancePremiumTier = laborInsuranceCalculationArray[19];

        } else if (salary <= laborInsuranceCalculationArray[20]) {
            insurancePremiumTier = laborInsuranceCalculationArray[20];

        } else if (salary <= laborInsuranceCalculationArray[21]) {
            insurancePremiumTier = laborInsuranceCalculationArray[21];

        } else if (salary <= laborInsuranceCalculationArray[22]) {
            insurancePremiumTier = laborInsuranceCalculationArray[22];

        } else if (salary <= laborInsuranceCalculationArray[23]) {
            insurancePremiumTier = laborInsuranceCalculationArray[23];

        } else if (salary <= laborInsuranceCalculationArray[24]) {
            insurancePremiumTier = laborInsuranceCalculationArray[24];

        } else if (salary <= laborInsuranceCalculationArray[25]) {
            insurancePremiumTier = laborInsuranceCalculationArray[25];

        } else {
            insurancePremiumTier = laborInsuranceCalculationArray[26];

        }
        return insurancePremiumTier;
    }



    private static int[] laborInsuranceAmount_11100 = {9, 17, 26, 36, 45, 53, 62, 71, 80, 88, 98, 107, 116, 124, 133, 142, 151, 160, 169, 178, 187, 195, 204, 213, 223, 231, 240, 249, 257, 266};
    private static int[] laborInsuranceAmount_12540 = {10, 20, 31, 40, 50, 60, 70, 81, 91, 100, 110, 120, 131, 141, 151, 160, 170, 181, 191, 201, 211, 220, 231, 241, 251, 261, 271, 280, 291, 301};
    private static int[] laborInsuranceAmount_13500 = {11, 22, 33, 44, 55, 64, 75, 86, 97, 108, 119, 130, 141, 152, 163, 172, 183, 194, 205, 216, 227, 238, 249, 260, 271, 280, 291, 302, 313, 324};
    private static int[] laborInsuranceAmount_15840 = {13, 25, 38, 50, 63, 76, 88, 101, 115, 127, 140, 152, 165, 178, 190, 203, 215, 228, 241, 253, 266, 279, 291, 304, 316, 329, 343, 355, 368, 380};
    private static int[] laborInsuranceAmount_16500 = {13, 26, 39, 52, 67, 80, 93, 106, 119, 132, 145, 158, 171, 184, 199, 212, 225, 238, 251, 264, 277, 290, 303, 316, 331, 344, 357, 370, 383, 396};
    private static int[] laborInsuranceAmount_17280 = {14, 27, 41, 56, 69, 83, 97, 110, 124, 139, 152, 166, 180, 193, 207, 221, 235, 249, 263, 276, 290, 304, 317, 332, 346, 359, 373, 387, 400, 415};
    private static int[] laborInsuranceAmount_17880 = {14, 28, 43, 57, 72, 86, 100, 115, 129, 143, 157, 171, 185, 201, 215, 229, 243, 257, 272, 286, 300, 314, 329, 344, 358, 372, 386, 400, 415, 429};
    private static int[] laborInsuranceAmount_19047 = {15, 31, 46, 61, 76, 92, 107, 122, 137, 153, 168, 183, 199, 214, 229, 243, 259, 274, 289, 304, 320, 335, 350, 365, 381, 396, 411, 427, 442, 457};
    private static int[] laborInsuranceAmount_20008 = {16, 32, 48, 64, 80, 96, 112, 128, 144, 160, 176, 192, 208, 224, 240, 256, 272, 288, 304, 320, 336, 352, 368, 384, 400, 416, 432, 448, 465, 480};
    private static int[] laborInsuranceAmount_21009 = {16, 34, 50, 68, 84, 100, 118, 134, 152, 168, 184, 202, 218, 236, 252, 269, 286, 302, 320, 336, 353, 370, 386, 404, 420, 437, 454, 470, 488, 504};
    private static int[] laborInsuranceAmount_22000 = {17, 35, 52, 71, 88, 106, 123, 141, 158, 176, 193, 212, 229, 247, 264, 281, 299, 316, 335, 352, 370, 387, 405, 422, 440, 457, 476, 493, 511, 528};
    private static int[] laborInsuranceAmount_23100 = {19, 37, 56, 74, 93, 111, 130, 148, 166, 184, 203, 221, 240, 259, 277, 296, 314, 333, 351, 370, 388, 407, 425, 444, 463, 480, 499, 517, 536, 554};
    private static int[] laborInsuranceAmount_24000 = {20, 38, 58, 76, 96, 116, 134, 154, 172, 192, 212, 230, 250, 268, 288, 308, 326, 346, 364, 384, 404, 422, 442, 460, 480, 500, 518, 538, 556, 576};
    private static int[] laborInsuranceAmount_25250 = {21, 40, 61, 81, 101, 121, 142, 161, 182, 202, 223, 242, 263, 283, 303, 323, 344, 363, 384, 404, 424, 444, 465, 484, 505, 525, 545, 565, 586, 607};
    private static int[] laborInsuranceAmount_26400 = {21, 43, 63, 84, 106, 127, 148, 169, 190, 212, 232, 253, 275, 296, 316, 338, 359, 380, 401, 422, 444, 465, 485, 507, 528, 549, 571, 591, 612, 634};
    private static int[] laborInsuranceAmount_27600 = {22, 44, 67, 88, 110, 132, 155, 177, 199, 220, 243, 265, 287, 309, 332, 353, 375, 397, 420, 442, 464, 485, 508, 530, 552, 574, 596, 619, 640, 662};
    private static int[] laborInsuranceAmount_28800 = {23, 46, 69, 92, 116, 139, 161, 184, 207, 230, 253, 276, 300, 323, 346, 369, 392, 415, 437, 460, 484, 507, 530, 553, 576, 599, 622, 645, 668, 692};
    private static int[] laborInsuranceAmount_30300 = {24, 48, 73, 97, 121, 145, 170, 194, 218, 242, 266, 291, 315, 339, 363, 388, 412, 436, 460, 484, 509, 533, 557, 581, 607, 631, 655, 679, 703, 728};
    private static int[] laborInsuranceAmount_31800 = {25, 51, 76, 101, 128, 153, 178, 204, 229, 254, 280, 305, 331, 356, 382, 407, 432, 458, 483, 508, 535, 560, 585, 611, 636, 661, 687, 712, 737, 764};
    private static int[] laborInsuranceAmount_33300 = {26, 53, 80, 107, 133, 160, 187, 213, 240, 266, 293, 320, 346, 373, 399, 427, 453, 480, 506, 532, 560, 586, 613, 639, 667, 693, 719, 746, 772, 800};
    private static int[] laborInsuranceAmount_34800 = {28, 56, 84, 111, 140, 167, 195, 223, 251, 278, 307, 334, 362, 389, 418, 445, 473, 501, 529, 556, 585, 612, 640, 668, 696, 724, 752, 780, 807, 836};
    private static int[] laborInsuranceAmount_36300 = {29, 58, 87, 116, 145, 175, 203, 232, 262, 290, 320, 348, 377, 407, 435, 465, 494, 523, 552, 580, 610, 639, 668, 697, 727, 755, 784, 813, 842, 872};
    private static int[] laborInsuranceAmount_38200 = {31, 61, 92, 122, 153, 183, 214, 244, 275, 305, 336, 367, 397, 428, 458, 489, 519, 550, 580, 611, 641, 672, 703, 733, 764, 794, 825, 855, 886, 916};
    private static int[] laborInsuranceAmount_40100 = {933, 964, 996, 1029, 1060, 1092, 1125, 1156, 1189, 1221, 1252, 1285, 1317, 1350, 1381, 1413, 1446, 1478, 1511, 1542, 1575, 1607, 1640, 1671, 1703, 1736, 1768, 1801, 1832, 1865};
    private static int[] laborInsuranceAmount_42000 = {34, 68, 100, 134, 168, 202, 236, 268, 302, 336, 370, 404, 436, 470, 504, 538, 572, 604, 638, 672, 706, 740, 772, 806, 840, 874, 908, 940, 974, 1008};
    private static int[] laborInsuranceAmount_43900 = {35, 70, 106, 141, 176, 211, 245, 281, 316, 351, 386, 421, 457, 492, 527, 562, 597, 632, 668, 703, 737, 772, 807, 843, 878, 913, 948, 983, 1019, 1054};
    private static int[] laborInsuranceAmount_45800 = {37, 73, 110, 146, 183, 220, 256, 293, 329, 367, 403, 440, 477, 513, 550, 586, 623, 660, 696, 733, 769, 806, 842, 879, 916, 952, 989, 1025, 1063, 1100};


    public static int laborInsuranceCalculation(int salary, int day) {
        int lobor = 0;
        //勞保級距
        int insurancePremiumTier = assessLaborInsuranceTier(salary);

        switch (insurancePremiumTier) {
            case 11100:
                lobor = laborInsuranceAmount_11100[--day];
                break;
            case 12540:
                lobor = laborInsuranceAmount_12540[--day];
                break;
            case 13500:
                lobor = laborInsuranceAmount_13500[--day];
                break;
            case 15840:
                lobor = laborInsuranceAmount_15840[--day];
                break;
            case 16500:
                lobor = laborInsuranceAmount_16500[--day];
                break;
            case 17280:
                lobor = laborInsuranceAmount_17280[--day];
                break;
            case 17880:
                lobor = laborInsuranceAmount_17880[--day];
                break;
            case 19047:
                lobor = laborInsuranceAmount_19047[--day];
                break;
            case 20008:
                lobor = laborInsuranceAmount_20008[--day];
                break;
            case 21009:
                lobor = laborInsuranceAmount_21009[--day];
                break;
            case 22000:
                lobor = laborInsuranceAmount_22000[--day];
                break;
            case 23100:
                lobor = laborInsuranceAmount_23100[--day];
                break;
            case 24000:
                lobor = laborInsuranceAmount_24000[--day];
                break;
            case 25250:
                lobor = laborInsuranceAmount_25250[--day];
                break;
            case 26400:
                lobor = laborInsuranceAmount_26400[--day];
                break;
            case 27600:
                lobor = laborInsuranceAmount_27600[--day];
                break;
            case 28800:
                lobor = laborInsuranceAmount_28800[--day];
                break;
            case 30300:
                lobor = laborInsuranceAmount_30300[--day];
                break;
            case 31800:
                lobor = laborInsuranceAmount_31800[--day];
                break;
            case 33300:
                lobor = laborInsuranceAmount_33300[--day];
                break;
            case 34800:
                lobor = laborInsuranceAmount_34800[--day];
                break;
            case 36300:
                lobor = laborInsuranceAmount_36300[--day];
                break;
            case 38200:
                lobor = laborInsuranceAmount_38200[--day];
                break;
            case 40100:
                lobor = laborInsuranceAmount_40100[--day];
                break;
            case 42000:
                lobor = laborInsuranceAmount_42000[--day];
                break;
            case 43900:
                lobor = laborInsuranceAmount_43900[--day];
                break;
            case 45800:
                lobor = laborInsuranceAmount_45800[--day];
                break;
        }


        return lobor;
    }


}
