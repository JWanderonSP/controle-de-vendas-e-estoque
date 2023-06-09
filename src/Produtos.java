public class Produtos {
    private String codigo;
    private String nome;
    private double valor;
    private int quantidadeEmEstoque;

    public Produtos(String codigo, String nome, double valor,int quantidadeEmEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.quantidadeEmEstoque=quantidadeEmEstoque;
    }
    @Override
    public String toString() {
        return "codigo=" + codigo + ", nome=" + nome + ", valor=" + valor + ", quantidadeEmEstoque="
                + quantidadeEmEstoque;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    
        
    }



    

