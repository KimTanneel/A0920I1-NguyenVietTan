package test;

public interface Fly {

    public default void IsFly(){
        System.out.println("tui đang bay");
    };
    public abstract  void VoCanh();
}
