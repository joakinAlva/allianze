export interface ITCEDADRECARGO {
  idEdadRecargo?: number;
  edadMin?: string;
  edadMax?: string;
  recargoAnterior?: number;
  recargoActual?: number;
}

export class TCEDADRECARGO implements ITCEDADRECARGO {
  constructor(
    public idEdadRecargo?: number,
    public edadMin?: string,
    public edadMax?: string,
    public recargoAnterior?: number,
    public recargoActual?: number
  ) {}
}

export function getTCEDADRECARGOIdentifier(tCEDADRECARGO: ITCEDADRECARGO): number | undefined {
  return tCEDADRECARGO.idEdadRecargo;
}
