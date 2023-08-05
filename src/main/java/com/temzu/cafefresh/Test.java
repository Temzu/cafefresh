package com.temzu.cafefresh;

import java.util.Arrays;

public class Test {

  public static void main(String[] args) {
    int[] nums = {1,2,3,4,5,6,7};
    int[] nums1 = {-1,-100,3,99};
    rotate(nums, 3);
    rotate(nums1, 2);
    System.out.println(Arrays.toString(nums));
    System.out.println(Arrays.toString(nums1));

  }

  public static void rotate(int[] nums, int k) {
    int length = nums.length;
    k = k % length;

    int[] tmp = Arrays.copyOf(nums, length);

    System.arraycopy(tmp, 0, nums, k, length - k);
    System.out.println(Arrays.toString(tmp));
    System.arraycopy(tmp, length - k, nums, 0, length - (length - k));
  }
}
