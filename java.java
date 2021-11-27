import java.util.Scanner;
class java{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("nhap gia tri: ");
        int soNuyen = input.nextInt();
        System.out.println ("gia tri vua nhap la: " + soNuyen);
        if (soNuyen > 0){
            System.out.println("so vua nhap la so duong");
        } else
        System.out.println("so vua nhap la so am");

    }
}