public class NodoAVL{
    public int elem;
    public int height;
    public NodoAVL left;
    public NodoAVL right;

    public NodoAVL(int v, NodoAVL l, NodoAVL r){
        elem = v;
        height = 0;
        left = l;
        right = r;
        
    }
}
