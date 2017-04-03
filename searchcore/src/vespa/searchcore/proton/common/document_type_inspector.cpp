// Copyright 2016 Yahoo Inc. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.

#include <vespa/fastos/fastos.h>
#include <vespa/log/log.h>
LOG_SETUP(".proton.common.document_type_inspector");

#include "document_type_inspector.h"

namespace proton {

DocumentTypeInspector::DocumentTypeInspector(const document::DocumentType &oldDocType,
                                             const document::DocumentType &newDocType)
    : _oldDocType(oldDocType),
      _newDocType(newDocType)
{
}

bool
DocumentTypeInspector::hasUnchangedField(const vespalib::string &name) const
{
    if (!_oldDocType.hasField(name) || !_newDocType.hasField(name)) {
        return false;
    }
    const document::Field &oldField = _oldDocType.getField(name);
    const document::Field &newField = _newDocType.getField(name);
    return oldField == newField;
}

} // namespace proton
