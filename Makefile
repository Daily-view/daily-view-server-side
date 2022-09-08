build_gradle:
	@echo "Formatting kt files..."
	./gradlew :api:ktlintFormat
	./gradlew :domain:ktlintFormat
	@echo "Complete built jar"
