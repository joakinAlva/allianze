export interface ITCUNIDADNEGOCIO {
  idUnidadNegocio?: number;
  unidadNegocio?: string;
}

export class TCUNIDADNEGOCIO implements ITCUNIDADNEGOCIO {
  constructor(public idUnidadNegocio?: number, public unidadNegocio?: string) {}
}

export function getTCUNIDADNEGOCIOIdentifier(tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO): number | undefined {
  return tCUNIDADNEGOCIO.idUnidadNegocio;
}
