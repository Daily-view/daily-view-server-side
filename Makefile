clean_up:
	@echo "Formatting kt files..."
	./gradlew :api:ktlintFormat
	./gradlew :domain:ktlintFormat
	@echo "Complete built jar"
