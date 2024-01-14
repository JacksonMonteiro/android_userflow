# User Flow

## Descrição
Um projeto básico que implementa um CRUD de usuários. Desenvolvido em Java, com o padrão de projeto MVP (Model - View - Presenter). Utilizando ROOM, um ORM nativo do Android para interações com banco de dados SQLite, e Retrofit 2 para consumo de APIs RESTFul.

## Padrões e Arquitetura
- **Arquitetura:** MVP - Model, View, Presenter.
- **Linguagem:** Java.
- **Banco de Dados:** SQLite / Room.

## Pré-requisitos para execução:
- Android Studio instalado.
- Dispositivo Android ou Emulador configurado.

## Instalação
1. Clone o Repositório:
    ```bash
    git clone https://github.com/JacksonMonteiro/android_userflow.git
    ```

2. Abra no Android Studio:
    - Abra o Android Studio.
    - Selecione "Open an existing Android Studio project".
    - Navegue até o diretório onde você clonou o repositório e selecione-o.

3. Execute o Projeto:
    - Conecte um dispositivo Android ou inicie um emulador.
    - Execute o aplicativo no Android Studio.

## Estrutura do Projeto
O projeto é organizado de maneira modularizada e segue uma estrutura clara para facilitar a manutenção e expansão. Abaixo está uma visão geral dos principais diretórios e suas responsabilidades:

**model**:

Contém as classes de modelo que representam a estrutura de dados do aplicativo.

**services**:

Armazena os arquivos relacionados ao Retrofit e configuração de consumo de API. 

**data/local**:

Este diretório local contém os arquivos relacionados ao banco de dados local. Atualmente, você pode encontrar aqui a implementação usando ROOM.

**UI**:

Contém subdiretórios para interfaces do usuário, adapters e outros componentes visuais.
    ui/user: Contém as telas e componentes relacionados aos usuários.

**presenters**:

Armazena os presenters, que são responsáveis por intermediar as interações entre a UI e as regras de negócios.

**utils**:
Contém métodos utilitários que podem ser reutilizados em todo o projeto para manter a consistência e facilitar a manutenção.

**contracts**:

Inclui interfaces ou classes abstratas que definem contratos entre os componentes do MVP (Model-View-Presenter).

**listeners**:

Contém classes ou interfaces que representam listeners para operações assíncronas ou eventos específicos, permitindo a execução de ações após essas operações.

A organização modular facilita a navegação e a compreensão do código, promovendo boas práticas de desenvolvimento e facilitando a colaboração entre os membros envolvidos no projeto. Essa estrutura oferece flexibilidade para futuras expansões e manutenções do projeto.

## Contato
<div> 
  <a href="https://www.instagram.com/jacksonmonteirop/" target="_blank"><img src="https://img.shields.io/badge/-Instagram-%23E4405F?style=for-the-badge&logo=instagram&logoColor=white" target="_blank"></a>
  <a href = "mailto:infor.jackson324@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
  <a href="https://www.linkedin.com/in/ojacksonmonteiro/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> 
</div>
