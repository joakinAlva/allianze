export interface ITCTIPOREGLA {
  idTipoRegla?: number;
  tipo?: string;
  segmento?: number;
}

export class TCTIPOREGLA implements ITCTIPOREGLA {
  constructor(public idTipoRegla?: number, public tipo?: string, public segmento?: number) {}
}

export function getTCTIPOREGLAIdentifier(tCTIPOREGLA: ITCTIPOREGLA): number | undefined {
  return tCTIPOREGLA.idTipoRegla;
}
