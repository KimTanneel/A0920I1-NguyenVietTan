package AbtracClass_Interface.Thuc_hanh.Animal_Edibal;

public class Chicken extends Animal implements Edible {
    @Override
    public String makeSound(){
        return "Chicken : cluck-cluck!";
    }
    @Override
    public String howToEat(){
        return "could be fried";
    }
}
