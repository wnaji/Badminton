# Badminton Application

## Environment Setup

1. Copy the template properties file:
```bash
cp src/main/resources/application.properties.template src/main/resources/application.properties
```

2. Set up environment variables:
```bash
# Database
export DB_USERNAME=your_db_username
export DB_PASSWORD=your_db_password

# API Key (for production)
export API_KEY_VALUE=your_api_key
```

3. Run the application:
```bash
# Development
./mvnw spring-boot:run -Dspring.profiles.active=dev

# Production
./mvnw spring-boot:run -Dspring.profiles.active=prod
```

## Security Notes
- Never commit sensitive data to the repository
- Keep your API keys and database credentials secure
- Use environment variables for sensitive data in production 