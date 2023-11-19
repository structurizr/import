# Changelog

## 1.7.0 (19th November 2023)

- Adds support for SVG images (https://github.com/structurizr/import/issues/10)

## 1.6.0 (26th October 2023)

- Fixes https://github.com/structurizr/import/issues/13 (Mermaid diagrams not working).
- Fixes https://github.com/structurizr/import/issues/14 (Date of ADRs can't be changed from UTC)
- Updated dependencies and minimum Java version (17).

## 1.5.0 (25th July 2023)

- Updated dependencies and minimum Java version (11).

## 1.4.1 (17th March 2023)

- Updated dependencies.

## 1.4.0 (5th March 2023)

- Documentation/decisions can now be added to components.

## 1.3.1 (26th February 2023)

- Sets content type correctly when importing PlantUML/Mermaid/Kroki diagrams.

## 1.3.0 (26th February 2023)

- Adds support for choosing whether PlantUML/Mermaid/Kroki diagrams should be imported as PNG or SVG.

## 1.2.1 (18th February 2023)

- Explicitly request PNG format from Mermaid servers.

## 1.2.0 (16th February 2023)

- __Breaking change__: Renamed artifact from `structurizr-documentation` to `structurizr-import`.
- __Breaking change__: Renamed `com.structurizr.documentation.importer` to `com.structurizr.importer.documentation`
- __Breaking change__: Removed templates.

## 1.1.1 (26th January 2023) 

- Fixes #3 (Images from hidden folders are imported).

## 1.1.0 (5th January 2023)

- Adds support for documentation at the container level.

## 1.0.2 (21st December 2022)

- Adds the section filename into the model when importing documentation. 

## 1.0.1 (8th March 2022)

- Initial version, based upon a split from [structurizr-java](https://github.com/structurizr/java) and [structurizr-java-extensions](https://github.com/structurizr/java-extensions).