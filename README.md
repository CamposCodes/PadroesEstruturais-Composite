# Composite — Sistema de Cardápio (Café & Bar)

<p align="center">
	<a href="https://www.ufjf.br/" rel="noopener">
		<img width=261 height=148 src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Logo_da_UFJF.png/640px-Logo_da_UFJF.png" alt="Logo UFJF">
	</a>
</p>

<h3 align="center">DCC078-2025.3-A — Aspectos Avançados em Engenharia de Software (UFJF/ICE)</h3>

---

## 📝 Sumário
- [Sobre](#sobre)
- [Diagrama de Classe](#diagrama)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Exemplo de Uso](#exemplo)
- [Como Executar e Testes](#testes)
- [Autor](#autor)

## 🧐 Sobre <a name="sobre"></a>
> **Disciplina:** DCC078 – Aspectos Avançados em Engenharia de Software   </br>
> **Projeto:** Demonstração do padrão Composite em um sistema de cardápio de Café & Bar   </br>
> **Docente:** Prof. Marco Antônio Pereira Araújo  </br>
> **Data de entrega:** 28/11/2025   </br>
> **Aluno:** [Gabriel Campos Lima Alves](#autor)  </br>

### Padrão Composite
Implementação do padrão **Composite** aplicada a um sistema de cardápio de café e bar. O foco é trabalhar com estruturas em árvore de forma uniforme, demonstrando:
- Uso de uma interface comum (`ItemCardapio`) para folhas (produtos) e compostos (seções)
- Composição de seções e subcategorias em profundidade arbitrária
- Cálculo recursivo automático de preços em toda a hierarquia
- Suporte a combos com desconto aplicado ao total
- Visualização clara de como estruturas recursivas podem ser simplificadas
- Tratamento uniforme de itens simples e complexos sem type casting

Também há uma interface de demonstração interativa inclusa: [Interface de Demonstração](https://camposcodes.github.io/PadroesEstruturais-Composite/)
<p align="center">
	<img src="./screenshot.png" alt="Captura da interface - Demonstração Composite" width="900" />
</p>

## 📐 Diagrama de Classe <a name="diagrama"></a>
O diagrama abaixo representa a arquitetura do projeto, enfatizando a composição de itens e a estrutura hierárquica:

<p align="center">
	<img src="./Composite.png" alt="Diagrama de Classe - Composite" width="800"/>
</p>


## 🚀 Funcionalidades <a name="funcionalidades"></a>
### Sistema de Cardápio - Café & Bar
- **ItemCardapio**: Interface que define o contrato comum para todos os itens (método `getDescricao()` e `getPreco()`)
- **Produto**: Classe Leaf que representa um item individual (nome, preço, calorias)
- **SecaoCardapio**: Classe Composite que agrupa itens (produtos ou outras seções) em profundidade arbitrária
- **CardapioBar**: Cliente que utiliza a estrutura e fornece operações de alto nível
- **Pedido**: Possibilita estruturas aninhadas de múltiplos níveis (seções, subcategorias, combos)

### Recursos
- ✅ Implementação do padrão Composite via interface `ItemCardapio`
- ✅ Estruturas aninhadas em profundidade arbitrária (testado até 4 níveis)
- ✅ Cálculo recursivo automático de preços sem necessidade de type checking
- ✅ Suporte a combos com desconto percentual automático (10% padrão)
- ✅ Classe `SecaoCardapio` como nó composite e `Produto` como leaf
- ✅ Operações de composição dinâmica (adicionar/remover itens)
- ✅ Cobertura de testes com JUnit 5 (8 testes incluídos)

##  Tecnologias <a name="tecnologias"></a>
- **Java 11+**
- **JUnit 5** - Framework de testes
- **Maven** - Gerenciamento de dependências
- **Git** - Controle de versão
- **HTML5 / CSS3 / JavaScript** - Interface interativa de demonstração


## 📊 Exemplo de Uso <a name="exemplo"></a>
```java
// Criando produtos individuais (folhas - Leaf)
Produto cafe = new Produto("Café Expresso", 5.00, 10);
Produto cappuccino = new Produto("Cappuccino", 8.50, 150);
Produto coxinha = new Produto("Coxinha", 6.50, 200);

// Criando seções (compostos - Composite)
SecaoCardapio bebidasQuentes = new SecaoCardapio("Bebidas Quentes");
bebidasQuentes.addItem(cafe);
bebidasQuentes.addItem(cappuccino);

SecaoCardapio salgados = new SecaoCardapio("Salgados");
salgados.addItem(coxinha);

// Criando um combo com desconto
SecaoCardapio comboCafe = new SecaoCardapio("Café da Manhã", true);
Produto paoQueijo = new Produto("Pão de Queijo", 4.00, 180);
comboCafe.addItem(paoQueijo);
comboCafe.addItem(cafe);

// Agregando em um menu principal
SecaoCardapio menuPrincipal = new SecaoCardapio("Menu Principal");
menuPrincipal.addItem(bebidasQuentes);
menuPrincipal.addItem(salgados);
menuPrincipal.addItem(comboCafe);

// Usando o cardápio
CardapioBar cardapio = new CardapioBar();
cardapio.setMenuPrincipal(menuPrincipal);

// Printando o cardápio completo (recursivo!)
System.out.println(cardapio.getCardapioCompleto());

// Calculando o preço total de forma transparente
System.out.println("Total: R$ " + String.format("%.2f", cardapio.getPrecoTotal()));

// Saída:
// Seção: Menu Principal
// Seção: Bebidas Quentes
// Produto: Café Expresso - R$ 5.00 (10 kcal)
// Produto: Cappuccino - R$ 8.50 (150 kcal)
// Seção: Salgados
// Produto: Coxinha - R$ 6.50 (200 kcal)
// Seção: Combos
// Combo: Café da Manhã - R$ 8.10
// Produto: Pão de Queijo - R$ 4.00 (180 kcal)
// Produto: Café Expresso - R$ 5.00 (10 kcal)
// Total: R$ 37.60
```

## 🧪 Como Executar e Testes <a name="testes"></a>
### Pré-requisitos
- Java 11 ou superior
- Maven 3.6+

### Comandos
```bash
# Compilar o projeto
mvn clean compile

# Executar testes
mvn test

# Executar teste específico (exemplo)
mvn test -Dtest=padroesestruturais.composite.CardapioBarTest

# Empacotar
mvn package
```

### Interface de Demonstração Interativa
- Abra a [demonstração interativa](https://camposcodes.github.io/PadroesEstruturais-Composite/) para visualizar o padrão em ação
- A interface apresenta:
  - Construção interativa de cardápios
  - Cálculo automático de preços com desconto em combos
  - Visualização em tempo real da hierarquia
  - Estatísticas dinâmicas
  - Exemplos práticos de uso

### Testes Inclusos
1. **deveRetornarCardapioCompletoDoBar** - Valida estrutura completa com múltiplos níveis
2. **deveCalcularPrecoTotalComDesconto** - Verifica cálculo de desconto em combo
3. **deveRetornarExcecaoCardapioSemMenuPrincipal** - Testa validação de menu vazio
4. **deveRetornarExcecaoAoCalcularPrecoSemMenu** - Testa cálculo de preço sem menu
5. **deveRetornarSecaoVazia** - Valida seção vazia
6. **deveRetornarEstruturaComQuatroNiveis** - Testa profundidade arbitrária
7. **deveAplicarDescontoApenasEmCombos** - Verifica lógica de combo vs. seção normal

## 👨‍💻 Autor <a name="autor"></a>
**Gabriel Campos Lima Alves**  
Matrícula: 202176005  
Email: campos.gabriel@estudante.ufjf.br  
GitHub: [@CamposCodes](https://github.com/CamposCodes)

---

*Projeto de uso acadêmico exclusivo para a disciplina DCC078 - UFJF*
