# from fastapi import FastAPI
# from pydantic import BaseModel

# app = FastAPI(title="Python AI Service", version="1.0.0")


# class QuestionRequest(BaseModel):
#     question: str


# @app.get("/health")
# def health():
#     return {"status": "healthy", "service": "python-ai-service"}


# @app.post("/ask")
# def ask_question(request: QuestionRequest):
#     # Replace this placeholder with your RAG retrieval and LLM code.
#     answer = f"Python AI service received your question: {request.question}"
#     return {"question": request.question, "answer": answer}

from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI(
    title="Python AI Service",
    version="1.0.0"
)

class QuestionRequest(BaseModel):
    question: str

@app.get("/health")
def health():
    return {
        "status": "healthy",
        "service": "python-ai-service"
    }

@app.post("/ask")
def ask_question(request: QuestionRequest):

    question = request.question.strip()

    if not question:
        return {
            "question": question,
            "answer": "Please enter a question."
        }

    if "rag" in question.lower():
        answer = (
            "RAG stands for Retrieval-Augmented Generation. "
            "It retrieves relevant information from documents "
            "or a knowledge base and provides that information "
            "to a language model to generate a grounded answer. "
            "The main steps are document loading, text chunking, "
            "embedding generation, vector search, and answer generation."
        )
    else:
        answer = (
            f"Your question is: {question}. "
            "This demo currently supports a predefined answer for RAG."
        )

    return {
        "question": question,
        "answer": answer
    }
