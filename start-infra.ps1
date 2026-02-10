$projects = @(
    @{ Path = "discovery-server"; Name = "Discovery Server (Eureka)" },
    @{ Path = "config-server";    Name = "Config Server" },
    @{ Path = "user-service";     Name = "User Service" },
    @{ Path = "expense-service";  Name = "Expense Service" }
)

foreach ($proj in $projects) {
    Write-Host "Starting $($proj.Name)..." -ForegroundColor Cyan
    Start-Process -FilePath "mvn" -ArgumentList "spring-boot:run" -WorkingDirectory "$PSScriptRoot\$($proj.Path)" -NoNewWindow
    if ($proj.Name -match "Config Server") {
        Start-Sleep -Seconds 20
    } elseif ($proj.Name -match "Discovery Server") {
         Start-Sleep -Seconds 10
    } else {
        Start-Sleep -Seconds 5
    }
}

Write-Host "All services started." -ForegroundColor Green
