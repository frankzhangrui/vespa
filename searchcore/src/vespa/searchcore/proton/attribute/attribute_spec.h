// Copyright 2017 Yahoo Inc. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.

#pragma once

#include <vespa/searchcommon/attribute/config.h>

namespace proton {

/**
 * A specification of an attribute vector an attribute manager should
 * instantiate and manage.
 */
class AttributeSpec
{
private:
    vespalib::string _name;
    search::attribute::Config _cfg;
    bool _hideFromReading;
    bool _hideFromWriting;
public:
    AttributeSpec(const vespalib::string &name,
                  const search::attribute::Config &cfg);
    AttributeSpec(const vespalib::string &name,
                  const search::attribute::Config &cfg,
                  bool hideFromReading,
                  bool hideFromWriting);
    AttributeSpec(const AttributeSpec &);
    AttributeSpec & operator=(const AttributeSpec &);
    AttributeSpec(AttributeSpec &&);
    AttributeSpec & operator=(AttributeSpec &&);
    ~AttributeSpec();
    const vespalib::string &getName() const { return _name; }
    const search::attribute::Config &getConfig() const { return _cfg; }
    bool getHideFromReading() const { return _hideFromReading; }
    bool getHideFromWriting() const { return _hideFromWriting; }
};

} // namespace proton

