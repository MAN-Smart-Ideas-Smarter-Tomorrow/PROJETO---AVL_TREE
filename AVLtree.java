public class AVLtree{
    public NodoAVL root;

    public AVLtree(){
        root = null;
    }

    public int height(NodoAVL t){
        return (t == null) ? -1 : t.height;
    }

    public int fator_balance(NodoAVL t){
        return height(t.left) - height(t.right);
    }

    public int max(int left_side, int right_side){
        return (left_side > right_side) ? left_side : right_side;
    }

    public NodoAVL balance(NodoAVL nodo){
        if(fator_balance(nodo) == 2){
            if(fator_balance(nodo.left) > 0){
                nodo = rotate_right(nodo);
            } else {
                nodo = rotate_left(nodo);
            }
        } else if(fator_balance(nodo) = -2) {
            if(fator_balance(nodo.right) < 0){
                nodo = rotate_left(nodo);
            } else {
                nodo = rotate_right_left(nodo);
            }
        }
        nodo.height = max(height(nodo.left), height(nodo.right) + 1);
        return nodo;

    }

    public NodoAVL insert(NodoAVL t, int x){
        if(t == null){
            t = new NodoAVL(x, null, null);
        } else if (x <= t.elem){
            t.left = insert(t.left, x);
        } else if(x > t.elem){
            t.right = insert(t.right, x);
        }

        t = balance(t);
        return t;
        
    }


}