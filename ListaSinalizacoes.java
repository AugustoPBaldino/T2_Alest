import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

    public class ListaSinalizacoes {
         
      private Nodo header;
      private Nodo trailer;
      private int size;

      private class Nodo {
        public Sinalizacao element;
        public Nodo next;
        public Nodo prev;
    
        public Nodo(Sinalizacao element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }
    
      public ListaSinalizacoes() {
        this.header = null;
        this.trailer = null;
        this.size = 0;
    }

    public void add(Sinalizacao element) {
        Nodo newNode = new Nodo(element);
    
        if (header == null) {
            header = newNode;
            trailer = newNode;
        } else {
            trailer.next = newNode;
            trailer = newNode;
        }
        size++;
    }
    
    public Map<String, Integer> moreSinalizations() {
        Map<String, Integer> map = new HashMap<>();
        int[] months = new int[13];
    
        Nodo current = header;
        while (current != null) {
            int month = current.element.getMesImplantacao();
            months[0]++;
            months[month]++;
            current = current.next;
        }
    
        int maxMonth = 1;
        for (int i = 2; i < months.length; i++) {
            if (months[i] > months[maxMonth]) {
                maxMonth = i;
            }
        }
    
        map.put("mes", maxMonth);
        map.put("quantidade", months[maxMonth]);
        return map;
    }

    public int getMonth(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    
        Nodo targetNode;
        if (index == size - 1) {
            targetNode = trailer;
        } else {
            targetNode = getNodeAtIndex(index);
        }
    
        return targetNode.element.getMesImplantacao();
    }


    private Nodo getNodeAtIndex(int index) {
        Nodo aux;
        if (index <= size / 2) {
            aux = header.next;
            for (int i = 0; i < index; i++) {
                aux = aux.next;
            }
        } else {
            aux = trailer;
            for (int i = size - 1; i > index; i--) {
                aux = aux.prev;
            }
        }
        return aux;
    }
    
    public LocalDate getMenorData() {
        if (size == 0) {
            return null;
        }

        Nodo current = header;
        LocalDate menorData = current.element.getDataImplantacao();
        while (current != null) {
            LocalDate dataImplantacao = current.element.getDataImplantacao();
            if (dataImplantacao.isBefore(menorData)) {
                menorData = dataImplantacao;
            }
            current = current.next;
        }
        return menorData;
    }

    public LocalDate getMaiorData() {
        if (size == 0) {
            return null;
        }

        Nodo current = header;
        LocalDate maiorData = current.element.getDataImplantacao();
        while (current != null) {
            LocalDate dataImplantacao = current.element.getDataImplantacao();
            if (dataImplantacao.isAfter(maiorData)) {
                maiorData = dataImplantacao;
            }
            current = current.next;
        }
        return maiorData;
    }
    
    public int size() {
        return size;
    }

    public void reset() {
        header = null;
        trailer = null;
        size = 0;
    }

    public Sinalizacao getSinalizacao(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    
        Nodo targetNode = getNodeAtIndex(index);
        return targetNode.element;
    }
    
    @Override
    public String toString() {
        String s = "";
        Nodo aux = header;
        while (aux != null) {
            s += aux.element + "\n";
            aux = aux.next;
        }
        return s;
    }


}

   


    
          

