clean_up:
	@echo "Formatting kt files..."
	./gradlew :api:ktlintFormat
	@echo "Complete built jar"
