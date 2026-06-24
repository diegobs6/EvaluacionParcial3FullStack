
# build-all.ps1
# Compila los 12 microservicios de EvaluacionParcial3FullStack en orden.
# Ejecutar desde la raiz del proyecto (donde estan las 12 carpetas).
 
$ErrorActionPreference = "Stop"
 
$servicios = @(
    "Eureka-Server",
    "Api-Gateway",
    "MicroService-Usuario",
    "MicroService-Categoria",
    "MicroService-Producto",
    "MicroService-Inventario",
    "MicroService-Carrito",
    "MicroService-Pedido",
    "MicroService-Pagos",
    "MicroService-Envio",
    "MicroService-Notificacion",
    "MicroService-Reporte"
)
 
$raiz = Get-Location
$fallidos = @()
$inicio = Get-Date
 
foreach ($servicio in $servicios) {
    Write-Host ""
    Write-Host "=====================================================" -ForegroundColor Cyan
    Write-Host " Compilando: $servicio" -ForegroundColor Cyan
    Write-Host "=====================================================" -ForegroundColor Cyan
 
    $ruta = Join-Path $raiz $servicio
 
    if (-not (Test-Path $ruta)) {
        Write-Host "  CARPETA NO ENCONTRADA: $ruta" -ForegroundColor Red
        $fallidos += $servicio
        continue
    }
 
    Set-Location $ruta
 
    try {
        & .\mvnw.cmd clean package -DskipTests
        if ($LASTEXITCODE -ne 0) {
            throw "mvnw devolvio codigo de salida $LASTEXITCODE"
        }
        Write-Host "  OK: $servicio" -ForegroundColor Green
    }
    catch {
        Write-Host "  FALLO: $servicio -> $_" -ForegroundColor Red
        $fallidos += $servicio
    }
    finally {
        Set-Location $raiz
    }
}
 
$duracion = (Get-Date) - $inicio
 
Write-Host ""
Write-Host "=====================================================" -ForegroundColor Yellow
Write-Host " RESUMEN" -ForegroundColor Yellow
Write-Host "=====================================================" -ForegroundColor Yellow
Write-Host "Tiempo total: $($duracion.Minutes) min $($duracion.Seconds) seg"
 
if ($fallidos.Count -eq 0) {
    Write-Host "Los 12 microservicios compilaron correctamente." -ForegroundColor Green
    Write-Host "Listo para: docker compose up --build" -ForegroundColor Green
}
else {
    Write-Host "Fallaron $($fallidos.Count) de 12:" -ForegroundColor Red
    foreach ($f in $fallidos) {
        Write-Host "  - $f" -ForegroundColor Red
    }
    Write-Host ""
    Write-Host "Revisa el log de arriba para cada uno antes de levantar Docker." -ForegroundColor Yellow
}