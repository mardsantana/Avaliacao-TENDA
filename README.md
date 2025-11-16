
# Desafio Técnico - Backend JAVA | Tenda

## 📘 Sobre o Projeto
Este projeto foi desenvolvido como parte da avaliação técnica da empresa Tenda, seguindo boas práticas de Clean Code, DDD, SOLID e separação de responsabilidades entre camadas.

O sistema implementa CRUD de Cupons, cobrindo regras de negócio como validação de código, valor de desconto, expiração e soft delete.

---

## 🧠 Architecture Haiku

### 🎯 Objetivos do Negócio
- Permitir gerenciamento seguro e eficiente de cupons
- Demonstrar domínio de Java 17 e Spring Boot com boas práticas

### 🔐 Restrições
- Operação exclusiva via API REST
- Foco exclusivo em backend
- Validação rigorosa das regras de negócio

### ⚙️ Atributos de Qualidade
Clean Code > Clareza de camadas > Testabilidade

### 🧱 Decisões de Design
- Java 17 + Spring Boot + Maven
- Estrutura em camadas: Domain, Service, Repository, Controller
- Validações com Jakarta Bean Validation
- Banco em memória H2 para testes locais
- Uso de Mapper para transformação entre entidades e DTOs

---

## 📂 Funcionalidades Implementadas

**Criar Cupom**
- **POST** `/api/coupons
```json
{
  "code": "M@rd$on9898",
  "description": "Desconto especial",
  "discountValue": 10,
  "expirationDate": "2025-12-31T23:59:59",
  "published": true
}
```
- **Response:**
```json
{
  "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "code": "MRDON9",
  "description": "Desconto especial",
  "discountValue": 10,
  "expirationDate": "2025-12-31T23:59:59",
  "status": "ACTIVE",
  "published": true,
  "redeemed": false
}
```

**Buscar Cupom por ID**
- **GET** `/api/coupons/{id}`
- **Response:**
```json
{
  "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "code": "MRDON9",
  "description": "Desconto especial",
  "discountValue": 10,
  "expirationDate": "2025-12-31T23:59:59",
  "status": "ACTIVE",
  "published": true,
  "redeemed": false
}
```

**Deletar Cupom (Soft Delete)**
- **DELETE** `/api/coupons/{id}`
- Soft delete: cupom marcado como DELETED sem perda de histórico
- Não permite deletar cupom já deletado
**Response**
- 204 No Content
---

### 🔄 Endpoints Principais
| Método  | Rota                                 | Descrição                                  |
|---------|--------------------------------------|--------------------------------------------|
| POST    | `/api/coupons`                       | Criar um novo Cupom                        |
| GET     | `/api/coupons/{id}`                 | Lista o Cupom pelo ID                      |
| DELETE  | `/api/coupons/{id}`                  | Deleta o Cupom sem deletar do Banco        |

---

## 📚 Documentação da API (Swagger)
Acesse a documentação interativa via Swagger em:

🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🛠️ Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Lombok
- JUnit 5 + Mockito
- H2 Database (local)
- Jakarta Bean Validation (JSR-380)

---

## 🚀 Como Executar
```bash
git clone https://github.com/mardsantana/Avaliacao-TENDA
cd Avaliacao-TENDA
./mvnw spring-boot:run
```

Acesse: [http://localhost:8080](http://localhost:8080)

---

## 👨‍💻 Autor
**Mardson Santos de Santana**  
Desenvolvedor Backend Java  
🔗 [linkedin.com/in/seu-perfil](https://www.linkedin.com/in/mardson-santana98-java/)

---

## 📄 Licença
Uso exclusivo para fins de avaliação técnica da empresa Tenda.
