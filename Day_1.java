package lab;

import java.util.Arrays;

public class Day_1 {
	public static void main(String[] args) {
		String s = " java utility LAB ";
		String text = "java is java and java rocks";
		int[] numbers = {55, 90, 12, 67};
		int[] a = {1, 3, 5};
	    int[] b = {2, 4, 6};
	    String[] paths = {
	            "/home/user/docs",
	            "/home/user/docs/reports",
	            "/home/user/music"
	        };
	    int[] customers = {101, 102, 999, 103};
	    Object[] nested = {1, new Object[]{2, 3}, new Object[]{4, new Object[]{5}}};
		System.out.println(strUtils(s));
		System.out.println(Palindrome("level"));
		System.out.println(Count(text, "Java"));
		System.out.println(LetterCaps("space"));
		System.out.println(Sorting(numbers));
		System.out.println(ReverseSort(numbers));
		System.out.println(MaxNum(numbers));
		System.out.println(MinNum(numbers));
		System.out.println(MerageArr(a, b));
		System.out.println(ItratemaxDepth(paths));
		System.out.println(ItrateCountOccurence(text, "Java"));
		ItrateVIPNumber(customers);
		System.out.println(ItrateNested(nested));
		System.out.println(maxDepthRecursive(paths));
		System.out.println(countOccurrencesRecursive(text, "Java"));
		processQueueRecursive(customers, 0);
		System.out.println(RecursionSumNested(nested));
	}
	
	//Lab-1--------------------------------------------------
	public static String strUtils(String str) {
		return str.trim().replaceAll("\\s+", " ");
	}
	
	public static boolean Palindrome(String str) {
		 String input = str.replaceAll("\\s+", " ").toLowerCase();
			  int left = 0;
		        int right = input.length() - 1;

		        while (left < right) {
		            if (input.charAt(left) != input.charAt(right)) {
		                return false; // mismatch
		            }
		            left++;
		            right--;
		        }

		        return true; 
		}
	
	public static int Count(String text, String word) {
		if(text == null || word == null || word.isEmpty()) {
			return 0;
		}
		
		String[] words = text.split("\\W+");
		int count = 0;
		for(String w: words) {
			if(w.equalsIgnoreCase(word)) {
				count++;
			}
		}
		return count;
	}
	
	public static String LetterCaps(String str) {
		String[] words = str.split(" ");
		StringBuilder firstCase = new StringBuilder();
		   for (String word : words) {
		        if (word.length() > 0) {
		        	firstCase.append(Character.toUpperCase(word.charAt(0)))
		                  .append(word.substring(1))
		                  .append(" ");
		        }
		    }
		return firstCase.toString().trim();
	}
	
	public static String Sorting(int[] arr) {
		if(arr == null) {
			return null;
		}
		Arrays.sort(arr);
		
		return Arrays.toString(arr);
	}
	
	public static String ReverseSort(int[] arr) {
		int n = arr.length;
		for(int i=0; i<n-1; i++) {
			for( int j=0; j < n-i-1; j++) {
				if(arr[j] < arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return Arrays.toString(arr);
	}
	
	public static int MaxNum(int[] num) {
		int max = num[0];
		for(int i=0; i<num.length; i++) {
			if(num[i] > max) {
				max = num[i];
			}
		}
		
		return max;
	}
	
	public static int MinNum(int[] num) {
		int min = num[0];
		for(int i=0; i<num.length; i++) {
			if(num[i] < min) {
				min = num[i];
			}
		}
		
		return min;
	}
	
	public static String MerageArr(int[] arr1, int[] arr2) {
		int[] merged = new int[arr1.length + arr2.length];
		int index = 0;
		for(int num : arr1) {
			merged[index++] = num;
		}
		
		for(int num:arr2) {
			merged[index++] = num;
		}
		
		return Arrays.toString(merged);
	}
	
	//Lab-2---------------------------------------------------------------
	
	public static int ItratemaxDepth(String[] paths) {
		int maxDepth = 0;
		 for (String path : paths) {
	            String[] segments = path.split("/");
	            int depth = 0; 
	            for (String seg : segments) {
	                if (!seg.isEmpty()) {
	                    depth++;
	                }
	            }
	            if (depth > maxDepth) {
	                maxDepth = depth;
	            }
	        }
		return maxDepth;
	}
	
    public static int pathDepth(String path) {
        if (path.endsWith("/")) path = path.substring(0, path.length() - 1);
        int index = path.lastIndexOf("/");
        if (index <= 0) return 1;
        else return 1 + pathDepth(path.substring(0, index));
    }

    public static int maxDepthRecursive(String[] paths) {
        int maxDepth = 0;
        for (String path : paths) {
            int depth = pathDepth(path);
            if (depth > maxDepth) maxDepth = depth;
        }
        return maxDepth;
    }
	
	public static String ItrateCountOccurence(String text, String word) {
		
		String[] words = text.split("\\W+");
		int count = 0;
		for(String w: words) {
			if(w.equalsIgnoreCase(word)) {
				count++;
			}
		}
		
		String result = word + " - " + count;
		return result;
	}
	
	public static int countOccurrencesRecursive(String text, String word) {
        if (text == null || word == null || text.length() < word.length()) return 0;

        if (text.substring(0, word.length()).equalsIgnoreCase(word)) {
            return 1 + countOccurrencesRecursive(text.substring(word.length()), word);
        } else {
            return countOccurrencesRecursive(text.substring(1), word);
        }
    }
	
	public static void ItrateVIPNumber(int[] customerId) {
		for(int id:customerId) {
			if(id == 999) {
				System.out.println("Found VIP, stopping service...");
				break;
			}
			System.out.println("Serving ID:" + id);
		}
	}
	
	public static void processQueueRecursive(int[] customerIds, int index) {
        if (index >= customerIds.length) return; // base case: end of array
        if (customerIds[index] == 999) {
            System.out.println("Found VIP, stopping service...");
            return;
        }

        System.out.println("Processing customer ID: " + customerIds[index]);
        processQueueRecursive(customerIds, index + 1); 
    }

	
	public static int ItrateNested(Object[] arr) {
	    int sum = 0;

	    for (Object elem : arr) {
	        if (elem instanceof Integer) {
	            sum += (Integer) elem;
	        } else if (elem instanceof Object[]) {
	            Object[] inner = (Object[]) elem;
	            for (Object innerElem : inner) {
	                if (innerElem instanceof Integer) {
	                    sum += (Integer) innerElem;
	                } else if (innerElem instanceof Object[]) {
	                    Object[] inner2 = (Object[]) innerElem;
	                    for (Object inner2Elem : inner2) {
	                        sum += (Integer) inner2Elem;
	                    }
	                }
	            }
	        }
	    }

	    return sum;
	}
	
	  public static int RecursionSumNested(Object[] arr) {
	        int sum = 0;

	        for (Object elem : arr) {
	            if (elem instanceof Integer) {
	                sum += (Integer) elem;
	            } else if (elem instanceof Object[]) {
	                sum += RecursionSumNested((Object[]) elem);
	            }
	        }

	        return sum;
	    }

}
