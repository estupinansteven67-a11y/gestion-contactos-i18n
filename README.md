# Gestión de Contactos - Java Swing (i18n)

**Asignatura:** Programación de Interfaces  
**Estudiante:** Steven Javier Estupiñán Guamán  
**Institución:** Universidad Politécnica Salesiana  

---

## 📌 Descripción del Proyecto
Aplicación de escritorio desarrollada en **Java Swing** para la gestión de contactos, optimizada con una interfaz accesible, layouts estructurados (`BorderLayout` y `GridBagLayout`) e **internacionalización (i18n)** dinámica para tres idiomas (Español, Inglés y Francés).

---

## 🚀 Características Principales
- **Diseño Visual Adaptativo:** Uso de paleta de colores de alto contraste (`#2C3E50`, `#27AE60`, `#C0392B`) y tipografía `Segoe UI`.
- **Internacionalización Dinámica:** Cambio de idioma en tiempo real sin reiniciar la aplicación mediante `ResourceBundle`.
- **Formatos Localizados:** Formateo automático de fechas según la región (`Locale.forLanguageTag`).
- **Operaciones de Formulario:** Inserción, eliminación y limpieza de registros en un componente `JTable`.

---

## 📁 Estructura del Proyecto
```text
gestion-contactos-i18n/
└── src/
    └── main/
        ├── java/
        │   └── GestionContactosGUI.java
        └── resources/
            ├── messages_es.properties
            ├── messages_en.properties
            └── messages_fr.properties
