export interface ITCRANGOPRIMA {
  idRangoPrima?: number;
  valorMin?: number;
  valorMax?: number;
  dividendos?: number;
  comision?: number;
}

export class TCRANGOPRIMA implements ITCRANGOPRIMA {
  constructor(
    public idRangoPrima?: number,
    public valorMin?: number,
    public valorMax?: number,
    public dividendos?: number,
    public comision?: number
  ) {}
}

export function getTCRANGOPRIMAIdentifier(tCRANGOPRIMA: ITCRANGOPRIMA): number | undefined {
  return tCRANGOPRIMA.idRangoPrima;
}
