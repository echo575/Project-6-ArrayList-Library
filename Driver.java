public class Driver {
    public static void main(String[] args){
        ArrayList<Integer> myList = new ArrayList<>();
        System.out.println(myList + "end of list");
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(40);
        myList.add(50);
        System.out.println(myList);
        myList.remove(3);
        System.out.println(myList);
        myList.remove(-1);
        System.out.println(myList);
        myList.add(0,100);
        System.out.println(myList);
    }
}
