# Build script para Windows PowerShell
# Uso: ./build.ps1

$ErrorActionPreference = 'Stop'

Write-Host '==> Limpando artefatos antigos (.class, .jar)...'
Get-ChildItem -Filter *.class -ErrorAction SilentlyContinue | Remove-Item -Force -ErrorAction SilentlyContinue
Remove-Item -Force sistema-entregas.jar -ErrorAction SilentlyContinue

Write-Host '==> Compilando fontes (.java)...'
javac *.java

Write-Host '==> Gerando JAR executável (sistema-entregas.jar)...'
jar --create --file sistema-entregas.jar --main-class Main *.class

Write-Host '==> Concluído.' -ForegroundColor Green
Write-Host 'Para executar:'
Write-Host '  java -jar sistema-entregas.jar' -ForegroundColor Yellow
Write-Host '  java -jar sistema-entregas.jar --demo' -ForegroundColor Yellow
