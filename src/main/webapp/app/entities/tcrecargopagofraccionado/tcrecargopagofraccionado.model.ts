export interface ITCRECARGOPAGOFRACCIONADO {
  idRecargoPagoFraccionado?: number;
  periodo?: string;
  porcentaje?: number;
}

export class TCRECARGOPAGOFRACCIONADO implements ITCRECARGOPAGOFRACCIONADO {
  constructor(public idRecargoPagoFraccionado?: number, public periodo?: string, public porcentaje?: number) {}
}

export function getTCRECARGOPAGOFRACCIONADOIdentifier(tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO): number | undefined {
  return tCRECARGOPAGOFRACCIONADO.idRecargoPagoFraccionado;
}
