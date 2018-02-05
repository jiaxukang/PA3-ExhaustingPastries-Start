import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * PA3Main.java, PA3-ExhaustingPastries assignment user input file name
 */

public class PA3Main {
    // find the best strategy for pastry to sell,find max pastry which customer
    // can buy.
	public static void main(String[] args) {
        Scanner in = null; // read file
        in = read(in, args);
        String a = in.nextLine(); // get first line
        int budget = getbudget(a);
        HashMap<String, int[]> data = dealdata(in); // create hashmap

        List<String> datas = new ArrayList<String>(data.keySet());
        Collections.sort(datas); // sort hashmap
        HashMap<String, Integer> newprice = new HashMap<String, Integer>();
        // create new hashmap to sort max price.
        loop(datas, data, newprice);// loop the pastry and find max price.
        int band = 0;
        System.out.println();
        List<String> names = new ArrayList<String>(newprice.keySet());
        Collections.sort(names); // sort hashmap
        int i = 0;
        String name = "";
        i = compare(budget, newprice, band, names, i, name); // find how many
        System.out.println(); // kinds
        System.out.println(
                "The max number of unique pastries that can be bought with $"
                        + budget + " is:" + i);
    }

    public static int getbudget(String a) {
        // TODO Auto-generated method stub
        int num = 0;
        try {// if incorrect budget input
            num = Integer.valueOf(a);
        } catch (Exception ex) {
            System.out.println("ERROR: Incorrect budget input");
            System.exit(1);
        }
        return num;
    }

    // read file
    public static Scanner read(Scanner in, String[] args) {
        // TODO Auto-generated method stub
        try {// if can not find file
            in = new Scanner(new File(args[0]));
        } catch (Exception ex) {
            System.out.println("ERROR: File not found");
            System.exit(1);
        }
        return in;
    }

    // loop the pastry and find their max price.
    public static void loop(List<String> datas, HashMap<String, int[]> data,
            HashMap<String, Integer> newprice) {
        int length = 0;
        int i = 0;
        for (String pastry : datas) {
            length = data.get(pastry).length;
            int currnum = 0;
            int[] sofar = new int[length];
            int max = 0;
            // find the max price for every pastry.
            max = pastrysum(length, currnum, sofar, data.get(pastry), max);
            System.out.println(pastry + " costs $" + max);// print max price and
            // pastry
            newprice.put(pastry, max); // store max price and pastry
            i += 1;
        }
    }

    // find strategy which customers can buy and get how many kinds
    public static int compare(int budget, HashMap<String, Integer> newprice,
            int band, List<String> names, int i, String name) {
        // TODO Auto-generated method stub
        if (budget < band) {

            return i; // return how many kinds
        }
        int min = 100000;
        for (String cake : names) {

            if (min > newprice.get(cake)) {
                min = newprice.get(cake);
                name = cake;
            }
            band = min;
        }
        System.out.println("Can buy " + name + " for $" + min);// print out

        names.remove(name);// remove already bought
        return compare(budget - min, newprice, band, names, i + 1, name);
    }

    // find the strategy first and return max price for one pastry.
    public static int pastrysum(int length, int currnum, int[] sofar,
            int[] js, int max) {


        if (length == currnum) {
            // find the max price
            max = process(sofar, max, length, js);

            return max;
        }
        // find the strategy.
        for (int i = 0; i <= length; i++) {
            sofar[currnum] = i;
            max = pastrysum(length, currnum + 1, sofar, js, max);
        }
        return max;

    }

    // find the max price for every pastry.
    public static int process(int[] sofar, int max, int length, int[] js) {
        int total = 0;
        int allprice = 0;
        int time = 1;
        for (int num : sofar) {
            total += num * time;
            time = time + 1;
        }
        // calculate all inches
        if (total <= length) {
            // chose right inches.
            int i = 0;
            for (int num : sofar) {
                allprice += num * js[i];
                i += 1;
            }
            // calculate the price
            max = getmax(allprice, max);
            // get the max price
        }
        return max;

    }

    // get max price.
    public static int getmax(int allprice, int max) {
        if (max <= allprice) {
            max = allprice;
            return max;
        } else {
            return max;
        }
    }

    // create hashmap for store all information
    public static HashMap<String, int[]> dealdata(Scanner in) {
        HashMap<String, int[]> datain = new HashMap<String, int[]>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] lines = line.split(": ");
            int[] numint = numdeal(lines); // deal with number line
            datain.put(lines[0], numint);
        }
        return datain;
    }

    // deal with number line, make it become arraylist.
    public static int[] numdeal(String[] lines) {
        String[] dataafter = lines[1].split(" ");
        int[] b = new int[dataafter.length];
        int i = 0;
        for (String num : dataafter) {
            int anum = 0;
            try {// Incorrect price input
                anum = Integer.valueOf(num);
            } catch (Exception ex) {
                System.out.println("ERROR: Incorrect price input");
                System.exit(1);
            }
            b[i] = anum;
            i = i + 1;
        }
        return b;
    }
}
