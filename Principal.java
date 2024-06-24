import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {

    public static void main(String[] args) {
        // Criar lista de funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        // Inserir todos os funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // Remover o funcionário “João” da lista
        Funcionario joao = null;
        for (Funcionario f : funcionarios) {
            if (f.getNome().equals("João")) {
                joao = f;
                break;
            }
        }
        if (joao != null) {
            funcionarios.remove(joao);
        }

        // Imprimir todos os funcionários com todas suas informações
        System.out.println("Lista de Funcionários:");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        for (Funcionario f : funcionarios) {
            String dataFormatada = f.getDataNascimento().format(dtf);
            String salarioFormatado = df.format(f.getSalario());
            System.out.println("Nome: " + f.getNome() + ", Data de Nascimento: " + dataFormatada +
                    ", Salário: R$" + salarioFormatado + ", Função: " + f.getFuncao());
        }

        // Aumentar salários em 10%
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.1"));
            f.setSalario(novoSalario);
        }

        // Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario f : funcionarios) {
            String funcao = f.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(f);
        }

        // Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Função: " + funcao);
            for (Funcionario f : funcionariosPorFuncao.get(funcao)) {
                String salarioFormatado = df.format(f.getSalario());
                System.out.println("   Nome: " + f.getNome() + ", Salário: R$" + salarioFormatado);
            }
        }

        // Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        for (Funcionario f : funcionarios) {
            int mesAniversario = f.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + f.getNome() + ", Data de Nascimento: " + f.getDataNascimento().format(dtf));
            }
        }

        // Encontrar e imprimir o funcionário mais velho
        Funcionario maisVelho = funcionarios.get(0);
        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = f;
            }
        }
        int idadeMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("\nFuncionário mais velho:");
        System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idadeMaisVelho);

        // Ordenar os funcionários por ordem alfabética
        funcionarios.sort((f1, f2) -> f1.getNome().compareTo(f2.getNome()));
        System.out.println("\nFuncionários por ordem alfabética:");
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", Data de Nascimento: " + f.getDataNascimento().format(dtf));
        }

        // Calcular e imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            totalSalarios = totalSalarios.add(f.getSalario());
        }
        String totalSalariosFormatado = df.format(totalSalarios);
        System.out.println("\nTotal dos salários dos funcionários: R$" + totalSalariosFormatado);

        // Calcular e imprimir quantos salários mínimos cada funcionário ganha
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos ganhos por cada funcionário:");
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println("Nome: " + f.getNome() + ", Salários Mínimos: " + salariosMinimos);
        }
    }
}
