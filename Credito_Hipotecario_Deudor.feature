# new feature
# Tags: optional
#es
Feature: Como Deudor de un prestamo hipotecario
  Necesito realizar mis pagos
  Con el fin de reducir y/o terminar mi deuda con el banco.

  Scenario: Abono mensual a deuda hipotecaria
    Given que hay una deuda vigente
    When el deudor hace el pago mensual
    Then descuenta el valor pagado a la deuda actual

  Scenario: Terminacion de contrato por pago de deuda
    Given que el deudor ha sido constante con los pagos
    When la deuda actual sea igual a cero
    Then Se termina el contrato del crédito hipotecario
    And se genera el paz y salvo
