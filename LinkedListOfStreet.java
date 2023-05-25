import java.util.HashMap;
import java.util.Map;

public class LinkedListOfStreet {
    
    private Node header;
    private Node trailer;
    private Node current;     
    private int count;
    
    private class Node {
        public Rua_Av_Trav element;
        public Node next;
        public Node prev;
        
        public Node(Rua_Av_Trav element) {
            this.element = element;
            next = null;
            prev=null;
        }
    }
    

    
    /**
     * Construtor da lista.
     */
    public LinkedListOfStreet() {
        header = new Node(null);
        trailer = new Node(null);
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    public void add(Rua_Av_Trav element) {
        
        Node n = new Node(element);
        
        n.next = trailer;
        n.prev = trailer.prev;
        
        trailer.prev.next = n;
        trailer.prev = n;
        
        count++;
    }
    
    public Rua_Av_Trav moreSinalizations() {
        int num = 0;
        Rua_Av_Trav element = null;
        Node aux = header.next;
    
        while (aux != null) {
            if (aux.element != null && aux.element.getLista().size() > num) {
                num = aux.element.getLista().size();
                element = aux.element;
            }
            aux = aux.next;
    
            if (aux == header) {
                break;
            }
        }
    
        return element;
    }
    
    
    
    public Map<String,String> monthMostSinalizations(String nomeEnd) {
        int index = indexOf(nomeEnd);
        Rua_Av_Trav element = get(index);
        Map<String, Integer> map = element.getLista().moreSinalizations();
        Map<String, String> map2 = new HashMap<String, String>(2);
        map2.put("mes", monthToString(map.get("mes")));
        map2.put("quantidade", map.get("quantidade").toString());
        return map2;
    }

    public Rua_Av_Trav get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = getNodeIndex(index);
        return aux.element;
    }

    public void add(int index, Rua_Av_Trav element) throws IndexOutOfBoundsException {
        if (index < 0 || index > count) 
            throw new IndexOutOfBoundsException();
        
    }

    private String monthToString(int month) {
        String[] months = {
            "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };
    
        if (month >= 1 && month <= 12) {
            return months[month - 1];
        } else {
            return "Mês inválido";
        }
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }    

    /**
     * Adiciona um elemento ao final da lista.
     * @param element elemento a ser adicionado ao final da lista
     */
    public void orderedAdd(Rua_Av_Trav element) {
        Node aux = containsElement(element.getNomeEnd()); // verifica se ja contem element para não inserir duplicado
        if (aux == null) { // se nao contem element, insere
            Node n = new Node(element);

            if (header.next == trailer) {
                // se a lista está vazia
                n.prev = header;
                n.next = trailer;
                trailer.prev = n;
                header.next = n;

            } else if (element.getNomeEnd().compareTo(header.next.element.getNomeEnd()) < 0) {
                // se for menor que o primeiro, insere no inicio
                n.next = header.next;
                n.prev = header;
                header.next = n;
                n.next.prev = n;
            } else if (element.getNomeEnd().compareTo(trailer.prev.element.getNomeEnd()) > 0) {
                // se for maior que o ultimo, insere no final
                n.next = trailer;
                n.prev = trailer.prev;
                trailer.prev.next = n;
                trailer.prev = n;
            } else {
                // senao procura a posicao correta para insercao
                aux = header.next;
                boolean inseriu = false;
                while (aux != trailer && !inseriu) {
                    if (element.getNomeEnd().compareTo(aux.element.getNomeEnd()) < 0) {
                        inseriu = true;
                        n.next = aux;
                        n.prev = aux.prev;
                        aux.prev.next = n;
                        aux.prev = n;
                    }
                    aux = aux.next;
                }
            }
            count++;
        }
    }
    
    
    
    private Node containsElement(String nomeEnd) {
        Node aux = header.next;
    
        while (aux != trailer) {
            if (aux.element.getNomeEnd().equals(nomeEnd)) {
                return aux;
            }
            aux = aux.next;
        }
    
        return null;
    }
    
    public boolean contains(String nomeEnd) {
        Node aux = header.next;
        for (int i = 0; i < count; i++) {
            if (aux.element.getNomeEnd().equals(nomeEnd)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
    
    public int indexOf(String nomeEnd) {
        Node aux = header.next;
        for (int i = 0; i < count; i++) {
            if (aux.element.getNomeEnd().equals(nomeEnd)) {
                return i;
            }
            aux = aux.next;
        }
        return -1;
    }
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
   
    
    // Metodo que tem como objetivo retornar uma referencia
    // para o nodo da posicao "index" recebida como parametro.
    // Por exemplo, se index for 2, ele retorna a referencia
    // para o nodo da posicao 2.
    private Node getNodeIndex(int index) {
        Node aux = null;
        if (index < count/2) { // caminha do inicio para o meio
            aux = header.next;
            for(int i=0; i<index; i++)
                aux = aux.next;
        }
        else { // caminha do fim para o meio
            aux = trailer.prev;
            for(int i=count-1; i>index; i--)
                aux = aux.prev;
        }
        return aux;
    }
    
    /**
     * Inicializa o current na primeira posicao (para percorrer do inicio para o fim).
     */
    public void reset() {
        current = header.next;
    }

    /**
     * Retorna o elemento da posicao corrente e faz current apontar para o proximo
     * elemento da lista.
     * @return elemento da posicao corrente
     */

    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node aux = header.next;
        for (int i = 0; i < count; i++) {
            s.append(aux.element);
            s.append("\n");
            aux = aux.next;
        }
        return s.toString();
    }    
    

}
