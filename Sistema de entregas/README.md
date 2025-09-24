# Sistema de Entregas (Java CLI)

Aplicação de console em Java 17 desenvolvida para fins acadêmicos (Engenharia da Computação). Permite gerenciar usuários e eventos com persistência local em arquivos, menu em português e modo demonstração.

## Funcionalidades
- **CRUD de Usuários**: cadastrar, listar e remover.
- **CRUD de Eventos**: cadastrar, listar e remover.
- **Categorias por Enum**: `Categoria` (`FESTA`, `SHOW`, `ESPORTE`, `CURSO`, `OUTROS`).
- **Datas Modernas**: `LocalDateTime` (Java Time API).
- **Persistência Local**: serialização binária (`usuarios.dat`, `eventos.dat`).
- **Menu em PT-BR**: interface de linha de comando amigável.
- **Modo Demonstração**: `--demo` para executar sem entrada de teclado.

## Requisitos
- **Java JDK 17+** (Temurin/Adoptium recomendado).
- Windows, macOS ou Linux (testado no Windows/PowerShell).

## Estrutura do Projeto
```
Sistema de entregas/
├─ Categoria.java       # Enum de categorias
├─ Usuario.java         # Modelo de usuário (Serializable)
├─ Evento.java          # Modelo de evento (Serializable)
├─ FileManager.java     # Persistência em arquivos .dat (serialização)
├─ Main.java            # Menu CLI, utilitários de input e modo --demo
├─ sistema-entregas.jar # JAR executável (gerado por jar)
├─ .gitignore           # Arquivo para ignorar artefatos locais
├─ LICENSE              # Licença MIT
└─ README.md            # Este arquivo
```

## Como Compilar e Executar
Entre na pasta do projeto pelo terminal e rode os comandos abaixo.

### Windows (PowerShell)
```powershell
# 1) Abrir a pasta do projeto
Set-Location "c:\Users\rodri\OneDrive\Rec Vap\Pública\MBA\Programação_Portifolio\Sistema de entregas"

# 2) (Opcional) Ajustar acentuação
chcp 65001

# 3) Compilar
javac *.java

# 4) Executar modo interativo (menu)
java Main

# 5) Executar modo demonstração (sem teclado)
java Main --demo
```

## Gerar e Executar o JAR
Após compilar (`javac *.java`), gere o JAR executável com:

```powershell
jar --create --file sistema-entregas.jar --main-class Main *.class
```

Execute o JAR:

```powershell
java -jar sistema-entregas.jar
```

Modo demonstração via JAR:

```powershell
java -jar sistema-entregas.jar --demo
```

### macOS/Linux (bash/zsh)
```bash
# 1) Abrir a pasta do projeto
cd "/caminho/para/Sistema de entregas"

# 2) Compilar
javac *.java

# 3) Executar (interativo)
java Main

# 4) Executar (demo)
java Main --demo
```

## Uso Rápido (Interativo)
- Opção `1`: Cadastrar usuário (ID, Nome, Email, Telefone, Endereço)
- Opção `4`: Cadastrar evento (ID, Nome, Data/Hora, Local, Categoria, Capacidade, Preço)
- Opção `5`: Listar eventos
- Opção `7`: Salvar dados
- Opção `0`: Sair (salva automaticamente)

## Modo Demonstração
Executa o sistema sem exigir entrada no teclado, populando alguns dados de exemplo e listando-os:
```bash
java Main --demo
```
Gera/atualiza os arquivos `usuarios.dat` e `eventos.dat` na pasta do projeto.

## Solução de Problemas
- **"java/javac não reconhecido"**: instale o JDK 17 e garanta `JAVA_HOME` e `%JAVA_HOME%\bin` no `PATH` (Windows) ou `PATH` no macOS/Linux.
- **Acentuação estranha no Windows**: execute `chcp 65001` antes de rodar.
- **Erro `NoSuchElementException`** ao executar em ambientes sem stdin: use `java Main --demo` ou rode em um terminal interativo.
- **Erro de compilação com `Main.Java`**: renomeie para `Main.java` (extensão ".java").

## Roadmap (Sugestões)
- **Validações**: prevenção de IDs duplicados e validação de campos.
- **Busca/Filtragem**: por nome, categoria e intervalo de datas.
- **Exportação CSV**: gerar relatórios de usuários e eventos.
- **Edição**: atualizar registros existentes.
- **Empacotamento**: script de build e `.jar` executável.

## Licença
Defina a licença conforme sua preferência (ex.: MIT). Exemplo: crie um arquivo `LICENSE` com o texto da licença.

## Autor
Rodrigo Pincelli — Projeto acadêmico de Engenharia da Computação.

Contatos (opcional):
- LinkedIn: https://www.linkedin.com/in/SEU-LINKEDIN
- GitHub: https://github.com/SEU-USUARIO
