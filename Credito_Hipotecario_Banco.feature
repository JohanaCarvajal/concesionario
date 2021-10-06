# new feature
# Tags: optional

#es
Feature: Como analista de crédito
  necesito poder validar en el aplicativo que el cliente cumpla las condiciones
  para aprobar un crédito hipotecario

  Rule La solicitud está diligenciada por el cliente

  Scenario: Comprobar edades para prestamo
    Given que la edad suministrada por el cliente
    When el cliente sea mayor o igual a una edad (definida por el banco)
    Then el prestamo es rechazado

  Scenario: Validación grado de escolaridad
    Given que el cliente debe tener nivel educativo
    When el nivel educativo es mayor o igual a bachillerato (bachillerato,universitario,especializacion,maestria)
    Then se aprueba esta etapa del credito

  Scenario: Evaluar tipo de contrato
    Given el tipo de contrato laboral suministrado por el cliente
    When el tipo de contrato sea igual a fijo o indefinido
    Then se aprueba esta etapa del credito

  Scenario: Evaluar capacidad de endeudamiento
    Given que conozco los ingresos mensuales del cliente
    And gastos mensuales
    When a ingreso le resto gastos
    And  el resultado es mayor a la cuota mensual pactada
    Then se aprobara esta etapa del credito
    But No significa que el credito fue aprobado

  Scenario: Evaluar codeudor
    Given que conozco los ingresos mensuales del cliente
    And  gastos mensuales
    When a ingreso le resto gastos
    And  el resultado es menor a la cuota mensual pactada
    Then se necesita codeudor
    But No significa que la etapa fue denegada

  Scenario: Evaluar historial crediticio
    Given la información financiera del cliente
    When aparece reportado en datacredito
    Then  el prestamo es rechazado

  Scenario: Desembolso de dinero
    Given que las etapas anteriores han sido aprobadas
    When el credito ha sido aprobado
    Then el banco desembolsará el dinero directamente a la inmobiliaria o vendedor del inmueble
