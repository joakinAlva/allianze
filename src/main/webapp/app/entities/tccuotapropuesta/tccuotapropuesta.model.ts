export interface ITCCUOTAPROPUESTA {
  idCuotaPropuesta?: number;
  edad?: string;
  valorVg?: number;
  valorBipTres?: number;
  valorBit?: number;
  valorDi?: number;
}

export class TCCUOTAPROPUESTA implements ITCCUOTAPROPUESTA {
  constructor(
    public idCuotaPropuesta?: number,
    public edad?: string,
    public valorVg?: number,
    public valorBipTres?: number,
    public valorBit?: number,
    public valorDi?: number
  ) {}
}

export function getTCCUOTAPROPUESTAIdentifier(tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA): number | undefined {
  return tCCUOTAPROPUESTA.idCuotaPropuesta;
}
