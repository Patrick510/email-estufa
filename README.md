# 📧 Microsserviço de Envio de E-mails - Estufa

Este microsserviço é responsável por receber dados críticos da estufa via **RabbitMQ** e enviar um e-mail com alerta detalhado.

---

## 💠 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Mail
- Spring Data JPA
- RabbitMQ
- MySQL
- Maven

---

## 📬 Funcionamento

- O microsserviço escuta mensagens da fila `ms.email` no RabbitMQ.
- Ao receber uma mensagem com os dados da estufa (temperatura, umidade, etc), ele monta o corpo do e-mail e envia para o endereço informado no payload.
- O e-mail do remetente está **estaticamente definido** na classe `EmailConsumer.java`:

```java
emailModel.setEmailFrom("patrick.dias@estudante.ifms.edu.br");
```

---

## 📅 Exemplo de Mensagem Recebida (JSON)

```json
{
  "temperaturaEstufa": 55.0,
  "sensacaoTermicaEstufa": 70.0,
  "umidadeEstufa": 40.0,
  "emailTo": "destinatario@email.com"
}
```

---

## ⚙️ Como rodar o projeto

### 📖 Pré-requisitos

- Java 17 ou superior
- MySQL ativo localmente
- RabbitMQ (ex: CloudAMQP)
- Maven instalado

### 🚀 Passos para execução

```bash
# 1. Clone o projeto
git clone https://github.com/Patrick510/email-estufa.git
cd email-estufa

# 2. Configure o application.properties:
# - URL e credenciais do banco de dados MySQL
# - E-mail e senha de app do Gmail
# - Endereço da fila RabbitMQ (ms.email)

# 3. Build do projeto
mvn clean install

# 4. Execute a aplicação
mvn spring-boot:run
```

---

## 📂 Estrutura da Fila RabbitMQ

- Fila escutada: `ms.email`
- Conversor de mensagens: `Jackson2JsonMessageConverter`
- Consumo realizado na classe: `EmailConsumer.java`

---

## 📨 Corpo do E-mail

O corpo do e-mail gerado é estruturado com os dados do ambiente:

```
Monitoramento:
Temperatura: 55.00°C
Sensção Térmica: 70.00°C
Umidade: 40.00%
```
