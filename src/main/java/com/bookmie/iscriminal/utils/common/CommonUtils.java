package com.bookmie.iscriminal.utils.common;

import java.util.Random;

public class CommonUtils {
  public static String generateEmailAuthCode() {
    Random rand = new Random();
    int min= 1000, max=9999;
    int code = rand.nextInt(max-min+1) + min;
   return String.valueOf(code); 
  }
}
