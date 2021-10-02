export interface ITCOCUPACION {
  idOcupacion?: number;
  ocupacion?: string;
  factorGiroAnterior?: number;
  factorGiroActual?: number;
}

export class TCOCUPACION implements ITCOCUPACION {
  constructor(
    public idOcupacion?: number,
    public ocupacion?: string,
    public factorGiroAnterior?: number,
    public factorGiroActual?: number
  ) {}
}

export function getTCOCUPACIONIdentifier(tCOCUPACION: ITCOCUPACION): number | undefined {
  return tCOCUPACION.idOcupacion;
}
