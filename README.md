# mpanel
`MPanel` é uma subclasse do `JPanel`, que age como um gerenciador de layout. A ideia é ter um visual parecido com o que você conseguiria utilizando uma série de `BoxLayouts`, mas com a facilidade de um `GridLayout`. `MPanel` é no fundo um `JPanel` com um `BoxLayout` na vertical e uma série de `JPanels` com `BoxLayouts` na horizontal.

# Utilização
Quando utiliza um `MPanel`, você primeiro ajusta algumas configurações opcionais, depois adiciona os componentes, e por fim chama o método `render()`, que vai adicionar os componentes indicados ao `JPanel` principal.

Para adicionar um componente, você usa uma das versões do método `add()`. A mais completa é:

`public void add(int line, int priority, int vGap, int hGap, JComponent ... components)`

onde `line` é a linha onde o componente vai ser adicionado, `priority` é a prioridade (quanto menor a prioridade, mais à esquerda o componente fica; componentes com a mesma prioridade ficam na ordem em que foram adicionados), `vGap` é o espaçamento vertical que vai ficar embaixo do componente, `hGap` é o espaçamento horizontal que vai ficar à direita do componente, e `components` é uma lista de componentes que serão adicionados.

Se `vGap` e `hGap` não forem informados, assume-se os valores padrão (`defaultVGap`, `defaultHGap`). Se `priority` não for informado, assume-se 0. Se `line` não for informado, assume-se a última linha adicionada.

Quando o `MPanel` identifica que uma `JLabel` e um `JTextField` estão sendo adicionados juntos em uma nova linha, ele automaticamente redimensiona os componentes (de acordo com as variáveis `lblSize` e `txtSize`) para que fiquem alinhados.

# Exemplos

## Exemplo 1 (Triangle.java)
![Imagem do Exemplo 1](https://i.imgur.com/ED4G7oB.png)

````java
    MPanel panel = new MPanel();

    panel.defaultHGap = 15;
    panel.defaultVGap = 5;
    panel.setMargin(15, 15);

    panel.add(0, lblL1, txtL1);
    panel.add(1, lblL2, txtL2);
    panel.add(2, lblL3, txtL3);
    panel.add(3, 0, 0, 5, lblVerif);
    panel.add(3, lblVerifR);
    panel.add(3, 0, 0, 5, lblCalc);
    panel.add(3, 0, 15, 0, lblCalcR);
    panel.add(4, btnCalc);
    panel.add(4, btnFechar);

    panel.render();
    add(panel);
````
    
## Exemplo 2 (Wage.java)
![Imagem do Exemplo 2](https://i.imgur.com/aaK0IKi.png)

````java
    MPanel panel = new MPanel();
    panel.setMargin(15, 15);
    panel.defaultVGap = 5;
    panel.defaultHGap = 15;
    panel.lblSize = new Dimension(170, 30);

    panel.add(0, lblNome, txtNome);
    panel.add(1, 0, 15, 15, lblTotalV, txtTotalV);
    panel.add(2, lblC);
    panel.add(3, 0, 15, 15, rb1, rb2, rb3, rb4);
    panel.add(4, lblValorC, txtValorC);
    panel.add(5, lblS, txtS);
    panel.add(6, 0, 15, 15, lblSC, txtSC);
    panel.add(7, btnCalc);

    panel.render();
    add(panel);
````
