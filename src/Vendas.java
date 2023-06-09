import java.time.LocalDate;
public class Vendas {
    LocalDate datavenda;
    Produtos produto;
    int Qntv=0;
  
    
    public Vendas(LocalDate data, Produtos produto, int qntv) {
        this.datavenda = data;
        this.produto = produto;
        Qntv = qntv;
    }
    public LocalDate getDatavenda() {
        return datavenda;
    }
    public void setDatavenda(LocalDate datavenda) {
        this.datavenda = datavenda;
    }
    public Produtos getProduto() {
        return produto;
    }
    public void setProduto(Produtos produto) {
        this.produto = produto;
    }
    public int getQntv() {
        return Qntv;
    }
    public void setQntv(int qntv) {
        Qntv = qntv;
    }
    @Override
    public String toString() {
        return  produto + ", Quantidade vendida=" + Qntv+"  Total venda: "+Qntv*getProduto().getValor();
    }


    
}
