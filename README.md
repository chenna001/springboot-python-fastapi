# Python + Spring Boot starter application

This starter runs two services:
- Python FastAPI: `http://localhost:8000`
- Java Spring Boot: `http://localhost:8080`

Spring Boot exposes `/api/ask` and forwards the JSON request to Python `/ask`.
The Python answer is a placeholder. Connect your RAG retrieval and LLM logic in `python-ai-service/app/main.py` when ready.

## Requirements
- Python 3.10 or newer
- Java 21
- Maven 3.9+ (or add a Maven wrapper)

## Start Python (Terminal 1, PowerShell)
```powershell
cd python-ai-service
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
python -m uvicorn app.main:app --reload --port 8000
```
Open `http://127.0.0.1:8000/docs` to test the Python API.

## Start Spring Boot (Terminal 2, PowerShell)
```powershell
cd springboot-service
mvn spring-boot:run
```

## Test through Spring Boot
Send a POST request to `http://localhost:8080/api/ask` with JSON:
```json
{
  "question": "What is RAG in AI?"
}
```
Example PowerShell request:
```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8080/api/ask -ContentType 'application/json' -Body '{"question":"What is RAG in AI?"}'
```
Health endpoints:
- `GET http://localhost:8000/health`
- `GET http://localhost:8080/api/health`
