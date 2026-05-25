package App.example;

import Model.DAO_Object;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        /*Scanner sc = new Scanner(System.in);
        System.out.println("\t>>>>>Server simulator<<<<<<");
        System.out.println("\t\t\t>>Menu<<");
        System.out.println("Digite o número da respectiva ação:");
        System.out.println("\t[1]: Rodar servidores\n\t[2]: Exibir relatório\n\t[3] Sair");
        System.out.print("Digite o número e pressione ENTER: ");
        int escolha = sc.nextInt();
        System.out.printf("Número escolhido: %d\n", escolha);
        while(escolha != 3){
            switch(escolha){
                case 1:
                    //função rodar servers
                    System.out.println("Servidores rolando!");
                    break;
                case 2:
                    //função relatório
                    System.out.println("Relatório exbido.");
                    break;
                default:
                    System.out.println("Digite uma opção válida - 1 a 3.");
            }
            System.out.println("Digite o número da respectiva ação:");
            System.out.println("\t[1]: Rodar servidores\n\t[2]: Exibir relatório\n\t[3] Sair");
            System.out.print("Digite o número e pressione ENTER: ");
            escolha = sc.nextInt();
            System.out.printf("Número escolhido: %d\n", escolha);
        }*/

        DAO_Object dao = new DAO_Object();
        /*for (int i = 1; i <= 5; i++){
            dao.saveObject("S1");
            dao.saveObject("S2");
            dao.saveObject("S3");
        }*/
        dao.display();

    }
}
