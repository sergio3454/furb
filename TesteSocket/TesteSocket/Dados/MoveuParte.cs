﻿using System.Runtime.Serialization;

namespace TesteSocket.Dados
{
    [DataContract]
    public class MoveuParte
    {
        [DataMember]
        public TipoDado tipo { get; set; }

        [DataMember]
        public int jogador { get; set; }

        [DataMember]
        public TipoParteCorpo parte { get; set; }

        [DataMember]
        public double offsetX { get; set; }

        [DataMember]
        public double offsetY { get; set; }
    }
}
