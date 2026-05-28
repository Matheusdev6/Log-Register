package App.example;

import Model.DAO_Object;

import java.util.Scanner;


public class App
{
    public static volatile boolean running = true; // sets running true, and readable for all threads
    public static void main( String[] args ) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        final int[] c1 = {0};
        final int[] c2 = {0};
        final int[] c3 = {0};
        System.out.println("\t>>>>>Server simulator<<<<<<");
        System.out.println("\t\t\t>>Menu<<");
        System.out.println("Type the number of the action, based on the caption below:");
        System.out.println("\t[1]: Run servers\n\t[2]: See report\n\t[3] Exit system");
        System.out.print("Type the number and press ENTER: ");
        int escolha = sc.nextInt();
        System.out.printf("Chosen number: %d\n", escolha);
        while(escolha != 3){
            switch(escolha){
                case 1:
                    var s1 = Thread.ofVirtual().unstarted(()->{
                        while(running){ // while is possible running, run
                            try{
                                DAO_Object dao = new DAO_Object();
                                dao.saveObject("S1");
                                c1[0]++;
                            } catch (Exception e){
                                System.out.println("Error: " + e.getMessage());
                                break;
                            }
                        }
                        System.out.println("Server 1 shutting down...");
                    });
                    var s2 = Thread.ofVirtual().unstarted(()->{
                        while(running){
                            try{
                                DAO_Object dao = new DAO_Object();
                                dao.saveObject("S2");
                                c2[0]++;
                            } catch (Exception e){
                                System.out.println("Error: " + e.getMessage());
                                break;
                            }
                        }
                        System.out.println("Server 2 shutting down...");
                    }); // defining the threads
                    var s3 = Thread.ofVirtual().unstarted(()->{
                        while(running){
                            try{
                                DAO_Object dao = new DAO_Object();
                                dao.saveObject("S3");
                                c3[0]++;
                            } catch (Exception e){
                                System.out.println("Error: " + e.getMessage());
                                break;
                            }
                        }
                        System.out.println("Server 3 shutting down...");
                    });
                    s1.start();
                    s2.start();
                    s3.start();
                    System.out.println("Type number 1 and press ENTER to shut the servers down: ");
                    while(true){
                        int num =  sc.nextInt(); // waiting for the user to type the number
                        if (num == 1){ // if the number "1" is written:
                            running = false; // running is not possible
                            s1.interrupt();
                            s2.interrupt(); // disable threads id they are in the timeout
                            s3.interrupt();
                            System.out.println("Servers shutting down...");
                            break;
                        }
                    }
                    try{
                        s1.join();
                        s2.join();
                        s3.join();
                        System.out.println("The servers were initialized and closed!");
                        System.out.printf("S1 requests: %d\n", c1[0]);
                        System.out.printf("S2 requests: %d\n", c2[0]);
                        System.out.printf("S3 requests: %d\n", c3[0]);
                        System.out.printf("Total requests: %d\n", c1[0]+c2[0]+c3[0]);
                        break;
                    } catch(InterruptedException e){
                        System.out.println("Error: " + e.getMessage());
                        break;
                    }
                case 2:
                    DAO_Object dao = new DAO_Object();
                    dao.display();
                    System.out.println("Report seen.");
                    break;
                default:
                    System.out.println("Type a valid option: from 1 to 3.");
            }
            System.out.println("Type the number of the action, based on the caption below:");
            System.out.println("\t[1]: Run servers\n\t[2]: See report\n\t[3] Exit system");
            System.out.print("Type the number and press ENTER: ");
            escolha = sc.nextInt();
            System.out.printf("Chosen number: %d\n", escolha);
        }
    }
}
