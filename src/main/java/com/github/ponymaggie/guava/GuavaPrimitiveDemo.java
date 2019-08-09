package com.github.ponymaggie.guava;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Shorts;

import java.util.List;

//处理原始类型
public class GuavaPrimitiveDemo {

    public static void main(String[] args) {

        //Bytes
        byte[] byteArray = {1, 2, 2, 3, 4, 5};
        List<Byte> byteList = Bytes.asList(byteArray);//如果用原生的，需要遍历复制过去
        System.out.println(byteList.toString());

        byteArray = Bytes.toArray(byteList);
        for(int i = 0; i < byteArray.length; i++){
            System.out.println("," + byteArray[i]);
        }

        System.out.println(Bytes.contains(byteArray, (byte)5));
        System.out.println(Bytes.indexOf(byteArray, (byte)5));
        System.out.println(Bytes.lastIndexOf(byteArray, (byte)2));

        //Shorts
        short[] shortArray = {10, 11, 12, 13};
        List<Short> shortList = Shorts.asList(shortArray);
        System.out.println(shortList);

        //short范围-32768~32767
        long valueL = 10000L;
        System.out.println(Shorts.checkedCast(valueL));
        valueL = 40000L;
//        System.out.println(Shorts.checkedCast(valueL));//这句会报异常

        System.out.println(Shorts.fromBytes((byte)1, (byte)2));//相当于1*256+2，注意不要溢出

        System.out.println(Shorts.join(":", shortArray));

    }

}
