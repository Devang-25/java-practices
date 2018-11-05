package test;

public class Node<E>
{
    E data;
    Node<E> next;

    public Node(E d)
    {
        this.data=d;
        this.next=null;

    }

    public E getData()

    {
        return data;
    }



    public void setData(E data) {
        this.data = data;
    }


   
}

