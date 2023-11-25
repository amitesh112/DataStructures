package bruteForceMaxProfit.driver;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import bruteForceMaxProfit.properties.Market;
import bruteForceMaxProfit.properties.CardData;
import bruteForceMaxProfit.properties.Price;



public class Driver {
    public static void main(String[] args)  throws Exception{
        String patternString = "(\\s*\\d+)+";
        Pattern pattern = Pattern.compile(patternString);

        //Price Data Declaration
        Price priceData = new Price();
        ArrayList<CardData> price_input = new ArrayList<>();
        ArrayList<Price> input = new ArrayList<>();


        int market_args=0;
        int price_args=0;


        //Argument Checking to READ input files
        for (int i = 0; i < args.length; i++) {

            if(args[i].equals("-m")){
                market_args=i+1;
            }
            if(args[i].equals("-p")){
                price_args=i+1;
            }
        }

        Market marketData = new Market();
        if (args.length == 4 ){
            BufferedReader reader;

            //Reading the market data file in a try-catch block and throw exception if
            // there is a size error if marketpricecards.size() ! marketprice.weight
            try{
                if(market_args==0){
                    throw new FileNotFoundException();
                }
                reader=new BufferedReader(new FileReader(args[market_args]));
                String line=reader.readLine();
                marketData.totalCards=Integer.parseInt(line);
                line=reader.readLine();
                while(line != null){
                    Matcher matcher = pattern.matcher(line);
                    if (!((Matcher) matcher).matches()) {
                        String[] data = line.split(" ");
                        if (data.length >= 2) {
                            try {
                                marketData.market_value.put(data[0],Integer.parseInt(data[1]));
                            } catch (NumberFormatException e) {
                                // Handle the exception, e.g. log an error message
                                System.out.println("Error");
                            }
                        }
                    }
                    line=reader.readLine();
                }
                int hmSize = marketData.market_value.size();
                if(marketData.totalCards != hmSize){
                    throw  new  Exception("Invalid Market List Size");
                }
            }catch (IOException e){
                e.printStackTrace();
            }


            //Reading Price Data file over here
            try{
                if(price_args==0){
                    throw new FileNotFoundException();
                }
                reader = new BufferedReader(new FileReader(args[price_args]));
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                ArrayList<String> initialData = new ArrayList<>();
                while (tokenizer.hasMoreTokens()) {
                    initialData.add(tokenizer.nextToken());
                }
                priceData.cards = Integer.parseInt(initialData.get(0));
                priceData.weight = Integer.parseInt(initialData.get(1));
                line = reader.readLine();


                //Read Price data file as individual sum till you hit the end of line
                while(line!=null){
                    //Reading the first line i.e Total Cards and weight
                    if(pattern.matcher(line).matches()){
                        priceData.entity=price_input;
                        input.add(priceData);
                        price_input = new ArrayList<>();
                        priceData = new Price();
                        String[] data = line.split(" ");
                        priceData.cards = Integer.parseInt(data[0]);
                        priceData.weight = Integer.parseInt(data[1]);
                    }

                    //Reading the Card List along with price
                    else{
                        String[] data = line.split(" ");
                        CardData val = new CardData();
                        val.name=data[0];
                        val.price=Integer.parseInt(data[1]);
                        price_input.add(val);
                    }
                    line = reader.readLine();
                }
                priceData.entity = price_input;
                input.add(priceData);
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found.");
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
            }



        }

        //Checking if we only have 5 command line arguments <program1 –m market_price.txt -p price_list.txt>
        if( args.length>4){
            throw new IllegalArgumentException("Number of Arguments are more than Expected!!");
        }
        if(args.length < 4){
            throw new IllegalArgumentException("Number of Arguments are less than Expected!!");
        }

        //************************************Brute Force KnapSack****************************************************//
        //************************************************************************************************************//
        //****************************Generating Max Profit Function Start here***************************************//


        try{
            //Creates a FileWriter
            FileWriter file = new FileWriter("output.txt");

            //Creates a BufferedWriter
            BufferedWriter output = new BufferedWriter(file);

            for(Price p:input){
                long startTime = System.nanoTime();
                int profit=0;
                int error=0;

                //All generated output cards will be stored in this variable
                ArrayList<CardData> outputTemp= new ArrayList<>();

                //Skip if the total cards size is more or less than the given cards
                if(p.cards != p.entity.size()){
                    output.write("Problem Skipped Due to Incorrect Card Values\n\n");
                    output.close();
                    continue;
                }

                //Starting with a base case->Consider the sum of all cards and check with the weight.
                int totalSum=0;
                ArrayList<CardData> cardRead=new ArrayList<>();
                for(CardData c:p.entity){
                    totalSum=totalSum+c.price;
                }
                //Calculating Profit considering the base case
                if(totalSum<=p.weight){
                    for(CardData c:p.entity){
                        profit=profit+(marketData.market_value.get(c.name)-c.price);
                    }
                    outputTemp = p.entity;
                }


                //If base case doesn't fullfill go ahead and create all subset combinations
                 else{
                    ArrayList<ArrayList<CardData>> subsets = new ArrayList<>();
                    int n = p.entity.size();

                    // Generating all subsets using binary counter
                    for (int i = 0; i < (1 << n); i++) {
                        ArrayList<CardData> subset = new ArrayList<>();
                        for (int j = 0; j < n; j++) {
                            if ((i & (1 << j)) > 0) {
                                subset.add(p.entity.get(j));
                            }
                        }
                        int currProfit=0;
                        int marketPrice=0;
                        //Now we have all combinations generated in subset ; let's iterate through that
                        for(CardData card: subset)
                        {
                            //Marking the error=1 if the card in price list does not match in market price.
                            try {
                                int m_price = marketData.market_value.get(card.name);
                                currProfit = currProfit + card.price;
                                marketPrice = marketPrice + (m_price - card.price);
                            }
                            catch(Exception e){
                                error =1;
                            }
                        }
                        if(currProfit<=p.weight){
                            if(profit<=marketPrice){
                                profit=marketPrice;
                                outputTemp=subset;
                            }
                        }
                    }
                }
                if(error ==1)
                {
                    output.write("Not Present inside market List "+"\n\n");
                    continue;
                }
                long estimatedTime = System.nanoTime() - startTime;
                double estimatedTimeSeconds= (double)estimatedTime / 1_000_000_000;
                DecimalFormat df = new DecimalFormat("#.#####");
                df.setRoundingMode(RoundingMode.CEILING);
                output.write(p.cards + " " + profit + " " + outputTemp.size() + " " + df.format(estimatedTimeSeconds) + "\n" );
                for (int i = 0; i < outputTemp.size(); i++) {
                    CardData card = outputTemp.get(i);
                    output.write(card.name +" "+ "\n");
                }
                output.write("\n");
            }
            output.close();
        } catch (Exception e) {
                e.printStackTrace();
            }
    }
}