import java.time.LocalDate;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<Produtos> listaDeProdutos=new ArrayList<>();
        List<Vendas> listaDeVendas=new ArrayList<>();
        DateTimeFormatter formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data=LocalDate.now();
        double total=0.0;
        int opcao=0;
        System.out.print("\033[H\033[2J"); 
        System.out.flush();
        System.out.println("Olá Bem vindo ao seu Sistema de Controle de Vendas!\n");
        do{
            System.out.println("\nO que você deseja fazer agora?");
            System.out.println("Para cadastrar um produto digite 1");
            System.out.println("Para adicionar produtos ao estoque digite 2");
            System.out.println("Para consultar um produtos cadastrado digite 3");
            System.out.println("Para Listar todos os produtos digite 4");
            System.out.println("Para listar vendas por período digite 5");
            System.out.println("Para cadastrar uma venda digite 6");
            System.out.println("Para sair digite 0");
            opcao=sc.nextInt();
            if(opcao<0 || opcao>6){
                System.out.println("\nOpa....Código errado! Vamos Tentar Novamente?\n");
            }
            else if(opcao==1){
                /*Cadastrar produtos */
                Boolean cadastrado=false;
                do{
                System.out.println("\nPor favor digite o código do produto que deseja cadastrar");
                String cod=sc.next();
                List<Produtos> novalistap = listaDeProdutos.stream()
                .filter(p -> p .getCodigo().equals(cod)).collect(Collectors.toList());;
                if(novalistap.isEmpty()){
                    cadastrado=true;
                System.out.println("Por Favor Digite o nome do produto:");
                String nomep=sc.next();
                System.out.println("Por Favor Digite o valor do produto:");
                Double vlr=sc.nextDouble();
                System.out.println("Deseja cadastrar estoque inicial? Digite 1 para Sim ou 2 para Não");
                        int opcao2=sc.nextInt();
                        if(opcao2==1){
                            System.out.println("Digite o estoque atual:");
                            int est=sc.nextInt();
                            listaDeProdutos.add(new Produtos(cod, nomep, vlr, est));
                            System.out.println("produto cadastrado com sucesso!");
                            break;
                        }
                        else{
                            listaDeProdutos.add(new Produtos(cod, nomep, vlr, 0));
                            System.out.println("produto cadastrado com sucesso!");
                        }
                
                         }
                        else{
                         System.out.println("Esse código já está sendo utilizado, por favor tente outro código.");
                         }
                         }while(cadastrado);
                         }

                else if(opcao==2){
                    /*Adicionar produtos ao estoque */
                    System.out.println("Por favor digite o código do produto que você deseja adicinar estoque:");
                    String cod=sc.next();
                    List<Produtos> novalistap1 = listaDeProdutos.stream()
                .filter(p -> p .getCodigo().equals(cod)).collect(Collectors.toList());;
                if(novalistap1.isEmpty()){
                    System.out.println("Não existe produto cadastrado com esse código!");
                }
                    else{
                        for (Produtos produtos : listaDeProdutos) {
                            System.out.println(produtos);
                            System.out.println("Digite a quantidade a ser adicionada:");
                            int q=sc.nextInt();
                            Produtos produtos1 = novalistap1.get(0);
                            produtos1.setQuantidadeEmEstoque(produtos1.getQuantidadeEmEstoque() + q);
                            System.out.println("O novo estoque é "+produtos.getQuantidadeEmEstoque());
                        }
                    }
                }

                    else if(opcao==3){
                        /*Consultar produto cadastrado */
                        System.out.println("Por favor digite o código do produto que você deseja listar:");
                        String cod=sc.next();
                        List<Produtos> novalistap2 = listaDeProdutos.stream()
                    .filter(p -> p .getCodigo().equals(cod)).collect(Collectors.toList());;
                    if(novalistap2.isEmpty()){
                        System.out.println("Não existe produto cadastrado com esse código!");
                    }
                        else{
                            for (Produtos produtos : listaDeProdutos) {
                                System.out.println(produtos);
                    }
                }
            }

                        else if(opcao==4){
                            /*Listar produtos */
                            System.out.println("*************************************");
                            System.out.println(data.format(formatada));
                            System.out.println("Relatório de estoque:\n");
                            for (Produtos produtos : listaDeProdutos) {
                                System.out.println ("\n"+ produtos);
                            System.out.println(".................................");
                            DoubleSummaryStatistics resumop = listaDeProdutos.stream()
                            .collect(Collectors.summarizingDouble(Produtos::getValor));
                            System.out.printf("Valor Médio: %.2f - Valor Máximo: %.2f - Valor Mínimo: %.2f",
                            resumop.getAverage(), resumop.getMax(), resumop.getMin());
                            System.out.println("\nFim do Relatório");
                        }
                    }

                                else if(opcao==5){
                                    /*Listar vendas por periodo */
                                    List<Vendas> novalistav2 = listaDeVendas.stream().toList();
                                    if(novalistav2.size()>0){
                                        System.out.println("***********************************");
                                    System.out.println("\nRelatório consolidado de vendas por período");
                                    List<LocalDate> datasVenda = listaDeVendas.stream()
                                     .map(Vendas::getDatavenda)
                                    .collect(Collectors.toList());
                                    LocalDate dataMinima = datasVenda.stream()
                                     .min(LocalDate::compareTo)
                                     .orElse(null);
                                    LocalDate dataMaxima = datasVenda.stream()
                                    .max(LocalDate::compareTo)
                                    .orElse(null);
                                    System.out.println("De: " + dataMinima.format(formatada) + " a " + dataMaxima.format(formatada));
                                    Map<LocalDate, List<Vendas>> vendasPorProduto = 
                                    listaDeVendas.stream().collect(Collectors.groupingBy(Vendas::getDatavenda));
                                    vendasPorProduto.entrySet().forEach(v -> System.out.println("Data da Venda:"+v.toString()) );
                                    System.out.println("...........................................");
                                    int t1=vendasPorProduto.entrySet().size();
                                    
                                    System.out.println("\nvalor médio das vendas por período "+total/t1);
                                    
                                    System.out.println("\nFim do Relatório");
                                
                                }
                                    else{
                                        System.out.println("Não há vendas a serem listadas.");
                                    }
                                }
                                        else if(opcao==6){
                                            /*Registrar venda */
                                            System.out.println("Digite o código do produto");
                                            String cod=sc.next();
                                             List<Produtos> novalistap3 = listaDeProdutos.stream()
                                            .filter(p -> p .getCodigo().equals(cod)).collect(Collectors.toList());;
                                             if(novalistap3.isEmpty()){
                                            System.out.println("Não existe produto cadastrado com esse código!");
                                            }
                                             else{
                                                System.out.println("Digite a quantidade vendida:");
                                                int qntve=sc.nextInt();
                                                if(novalistap3.get(0).getQuantidadeEmEstoque()>qntve){
                                                System.out.println("Digite a data da venda:");
                                                System.out.println("Primeiro o dia:");
                                                int dia=sc.nextInt();
                                                System.out.println("Agora o mês:");
                                                int mes=sc.nextInt();
                                                System.out.println("E o ano:");
                                                int ano=sc.nextInt();
                                                LocalDate data2=LocalDate.of(ano, mes, dia);
                                                Vendas vnd=new Vendas(data2,novalistap3.get(0), qntve);
                                                listaDeVendas.add(vnd);
                                                Produtos produtos4 = novalistap3.get(0);
                                                produtos4.setQuantidadeEmEstoque(produtos4.getQuantidadeEmEstoque() - qntve );
                                                System.out.println("O estoque restante é de "+produtos4.getQuantidadeEmEstoque());
                                                total= total+produtos4.getValor()*qntve;
                                                 }
                                                 else{
                                                    System.out.println("No momento não há estoque suficiente para realizar essa venda!");
                                                 }
                                                }

                                                }
            if(opcao==0){
                System.out.println("\nObrigado por utilizar nosso sistema. Até a Próxima!");
                break;
            }
        }while(opcao>=0 || opcao<7);
            
        sc.close();




        
        
    }
}
