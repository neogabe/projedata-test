import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 - Inserir todos os funcionários
        funcionarios.add(new Funcionario("João", LocalDate.of(1985, 5, 10), new BigDecimal("2500.00"), "Analista"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1990, 9, 15), new BigDecimal("3000.00"), "Gerente"));
        funcionarios.add(new Funcionario("Pedro", LocalDate.of(1988, 7, 20), new BigDecimal("2000.00"), "Desenvolvedor"));

        // 3.2 - Remover o funcionário "João" da lista
        Iterator<Funcionario> iterator = funcionarios.iterator();
        while (iterator.hasNext()) {
            Funcionario funcionario = iterator.next();
            if (funcionario.getNome().equals("João")) {
                iterator.remove();
                break;
            }
        }

        // 3.3 - Imprimir todos os funcionários com todas as informações
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00",
                new DecimalFormatSymbols(new Locale("pt", "BR")));

        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
            System.out.println("Salário: " + decimalFormat.format(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("-------------------");
        }

        // 3.4 - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtualizado = funcionario.getSalario().multiply(new BigDecimal("1.1"));
            funcionario.setSalario(salarioAtualizado);
        }

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        // 3.6 - Imprimir os funcionários, agrupados por função
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Funcionários da função " + funcao + ":");
            List<Funcionario> funcionariosPorFuncaoAtual = funcionariosPorFuncao.get(funcao);
            for (Funcionario funcionario : funcionariosPorFuncaoAtual) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
                System.out.println("Salário: " + decimalFormat.format(funcionario.getSalario()));
                System.out.println("-------------------");
            }
        }

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("Funcionários que fazem aniversário em outubro e dezembro:");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
                System.out.println("-------------------");
            }
        }

        // 3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade
        Funcionario funcionarioMaisVelho = null;
        int idadeMaisVelho = 0;
        for (Funcionario funcionario : funcionarios) {
            int idade = LocalDate.now().getYear() - funcionario.getDataNascimento().getYear();
            if (idade > idadeMaisVelho) {
                idadeMaisVelho = idade;
                funcionarioMaisVelho = funcionario;
            }
        }
        if (funcionarioMaisVelho != null) {
            System.out.println("Funcionário mais velho:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + idadeMaisVelho);
            System.out.println("-------------------");
        }

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("Lista de funcionários por ordem alfabética:");
        funcionarios.sort((f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()));
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
            System.out.println("Salário: " + decimalFormat.format(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("-------------------");
        }

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("Total dos salários dos funcionários: " + decimalFormat.format(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00
        System.out.println("Salários em múltiplos do salário mínimo:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(new BigDecimal("1212.00"), 2, BigDecimal.ROUND_DOWN);
            System.out.println("Funcionário: " + funcionario.getNome());
            System.out.println("Salários mínimos: " + salariosMinimos);
            System.out.println("-------------------");
        }
    }
}