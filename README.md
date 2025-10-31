# Sistema de Controle de Reservas de Hotel

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Build](https://img.shields.io/badge/Status-Concluído-4c1?style=for-the-badge)

Projeto desenvolvido para o curso Full Stack Java da EBAC. O objetivo é um sistema de console (CLI) para gerenciar reservas de hotel, aplicando conceitos de Java, Orientação a Objetos e uma arquitetura de software profissional em camadas.

## Funcionalidades

O sistema oferece um menu interativo com as seguintes opções:

*  **Cadastrar Reserva:** Adiciona uma nova reserva ao sistema. O sistema valida dados de entrada (dias e valores positivos) e regras de negócio (capacidade máxima de 10 reservas).
*  **Listar Reservas:** Exibe todas as reservas cadastradas, formatadas com seus detalhes e valor total da estadia.
*  **Buscar Reserva:** Permite a busca de reservas por parte do nome do hóspede (sem diferenciar maiúsculas de minúsculas).
*  **Ordenar Reservas:** Ordena a lista de reservas em ordem decrescente (do maior para o menor) com base no número de dias da estadia.
*  **Tratamento de Erros:** O sistema é robusto e trata entradas inválidas do usuário (ex: letras em campos numéricos) sem quebrar a execução.

## Arquitetura do Projeto

O projeto utiliza uma arquitetura em camadas para separar as responsabilidades (Separation of Concerns - SoC):

* **`Aplicacao.java`** (Camada de Inicialização): Ponto de entrada (`main`) que "liga os fios", injetando o `ReservaService` no `SistemaHotel`.
* **`SistemaHotel.java`** (Camada de Visão/UI): Responsável *apenas* pela interação com o usuário (menu, `Scanner`, `System.out.println`).
* **`ReservaService.java`** (Camada de Serviço): Responsável por *toda* a lógica de negócio (adicionar, listar, buscar, ordenar, verificar lotação).
* **`Reserva.java`** (Camada de Modelo): Objeto de dados (POJO) que representa uma reserva e suas validações internas.
* **`TipoQuarto.java`** (Modelo/Enum): Garante a segurança de tipos para os quartos, evitando erros de digitação.
* **`HotelLotadoException.java`** (Exceção Customizada): Uma exceção de negócio específica para a regra de capacidade do hotel.

### Estrutura de Arquivos

```
src/
├── Aplicacao.java           # (Inicia a aplicação)
├── SistemaHotel.java        # (Camada de UI)
├── ReservaService.java      # (Camada de Lógica de Negócio)
├── Reserva.java             # (Camada de Modelo)
├── TipoQuarto.java          # (Enum)
└── HotelLotadoException.java  # (Exceção Customizada)
```

##  Tecnologias Utilizadas

* **Java 21+**
* **Programação Orientada a Objetos (OOP):** Encapsulamento, Polimorfismo (toString), Abstração.
* **Collections Framework:** Uso de `List` e `ArrayList` para gerenciamento de dados.
* **Tratamento de Exceções:** `try-catch`, `IllegalArgumentException` e exceções customizadas.
* **Arquitetura em Camadas:** Separação clara entre Visão, Serviço e Modelo.
* **Injeção de Dependência (DI):** Injeção manual do `ReservaService` no `SistemaHotel`.
* **Enums:** Para segurança de tipos (Tipo de Quarto).
* **API de `Comparator`:** Para ordenação customizada da lista.

## Como Executar

### Pré-requisitos

* JDK (Java Development Kit) 21 ou superior instalado e configurado no `PATH`.

### Executando via Linha de Comando

1.  **Clone o repositório:**
    ```bash
    git clone [COLE A URL DO SEU REPOSITÓRIO AQUI]
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd nome-da-pasta-do-projeto
    ```

3.  **Compile todos os arquivos Java dentro do `src`:**
    ```bash
    javac src/*.java
    ```

4.  **Execute a aplicação (a partir da raiz do projeto):**
    ```bash
    java -cp src Aplicacao
    ```

### Executando via IDE

1.  Importe o projeto na sua IDE favorita (IntelliJ, Eclipse, VS Code).
2.  Localize o arquivo `src/Aplicacao.java`.
3.  Clique com o botão direito e selecione "Run 'Aplicacao.main()'".

---

Desenvolvido por **Vinicius Pires**.
