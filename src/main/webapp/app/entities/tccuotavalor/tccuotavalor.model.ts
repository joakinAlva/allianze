export interface ITCCUOTAVALOR {
  idCuotaValor?: number;
  porcentaje?: number;
  valor?: string;
}

export class TCCUOTAVALOR implements ITCCUOTAVALOR {
  constructor(public idCuotaValor?: number, public porcentaje?: number, public valor?: string) {}
}

export function getTCCUOTAVALORIdentifier(tCCUOTAVALOR: ITCCUOTAVALOR): number | undefined {
  return tCCUOTAVALOR.idCuotaValor;
}
