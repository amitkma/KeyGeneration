public class KeyGeneration {

    public static void main(String[] args) {

        KeyGeneration keyGeneration = new KeyGeneration();
        System.out.println(keyGeneration.makeKey(2725844759l));
    }

    private String makeKey(long seed){
        String result = String.format("%08X", seed);
        String keyByte1 = Long.toHexString(getKeyByte(seed, 24,3,200));
        keyByte1 = keyByte1.substring(keyByte1.length() - 2, keyByte1.length());
        String keyByte2 = Long.toHexString(getKeyByte(seed, 10,0,56));
        keyByte2 = keyByte2.substring(keyByte2.length() - 2, keyByte2.length());
        String keyByte3 = Long.toHexString(getKeyByte(seed, 1,2,91));
        keyByte3 = keyByte3.substring(keyByte3.length() - 2, keyByte3.length());
        String keyByte4 = Long.toHexString(getKeyByte(seed, 7,1,100));
        keyByte4 = keyByte4.substring(keyByte4.length() - 2, keyByte4.length());
        result =result+keyByte1.toUpperCase()+keyByte2.toUpperCase()+keyByte3.toUpperCase()+keyByte4.toUpperCase();
        result = result+getChecksum(result);
        //result = formatResult(result);
        return result;
    }

    private long getKeyByte(long seed, int a, int b, int c) {
        a = a % 25;
        b = b % 3;

        if (a % 2 == 0) {
            return ((seed >> a) & 255) ^ ((seed >> b) | c);
        } else {
            return ((seed >> a) & 255) ^ ((seed >> b) & c);
        }
    }

    private String getChecksum(String s) {
        int left = 0x0056;
        int right = 0x00AF;

        if (s.length() > 0) {
            for (int i = 0; i < s.length(); i++) {
                right = right + s.charAt(i);
                if(right > 0xFF){
                    right = right - 0xFF;
                }
                left = left + right;
                if(left > 0xFF){
                    left = left - 0xFF;
                }
            }
        }

        int sum = (left << 8)+ right;
        return Integer.toHexString(sum);
    }
}
