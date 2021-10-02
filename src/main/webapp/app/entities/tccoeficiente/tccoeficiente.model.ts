export interface ITCCOEFICIENTE {
  idCoeficiente?: number;
  coeficiente?: number;
  intervaloMin?: number;
  intervaloMax?: number;
  descuentoExtra?: number;
}

export class TCCOEFICIENTE implements ITCCOEFICIENTE {
  constructor(
    public idCoeficiente?: number,
    public coeficiente?: number,
    public intervaloMin?: number,
    public intervaloMax?: number,
    public descuentoExtra?: number
  ) {}
}

export function getTCCOEFICIENTEIdentifier(tCCOEFICIENTE: ITCCOEFICIENTE): number | undefined {
  return tCCOEFICIENTE.idCoeficiente;
}
